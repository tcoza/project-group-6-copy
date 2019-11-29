package ca.mcgill.ecse321.tutoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;

import cz.msebera.android.httpclient.Header;

public class StudentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        getSupportActionBar().setTitle("Students");

        GetStudentsResponseHandler handler = new GetStudentsResponseHandler((TableLayout) findViewById(R.id.table));
        try { HttpUtils.get("/students", new RequestParams(), handler); } finally {}

    }

    private void addStudent(TableLayout table, String username, String first, String last, boolean active)
    {
        TableRow row = new TableRow(this);
        username = truncate(username, 15);
        first = truncate(first, 10);
        last = truncate(last, 10);
        for (String data : new String[]{username, first, last})
        {
            TextView text = new TextView(this);
            text.setTextSize(16);
            text.setText(data);
            text.setPadding(0,15,0,0);
            row.addView(text);
        }
        CheckBox activeCheckBox = new CheckBox(this);
        activeCheckBox.setChecked(active);
        row.addView(activeCheckBox);
        table.addView(row);
    }

    private String truncate(String s, int l) { return s.length() > l ? s.substring(0,l-3) + "..." : s; }

    private class GetStudentsResponseHandler extends JsonHttpResponseHandler
    {
        private TableLayout table;
        public GetStudentsResponseHandler(TableLayout table) { this.table = table; }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response)
        {
            try
            {
                for (int i = 0; i < response.getJSONObject("_embedded").getJSONArray("students").length(); i++)
                {
                    JSONObject student = response.getJSONObject("_embedded").getJSONArray("students").getJSONObject(i);
                    String username = student.getJSONObject("_links").getJSONObject("self").getString("href");
                    username = username.substring(username.lastIndexOf('/') + 1);
                    addStudent(
                            table,
                            username,
                            student.getString("firstName"),
                            student.getString("lastName"),
                            student.getBoolean("isActiveAccount"));
                }
            }
            catch (JSONException ex)
            {
                Log.e("network", ex.getMessage());
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
        {
            StudentsActivity.this.getSupportActionBar().setTitle("Error loading Students: " + throwable.getMessage());
        }
    }
}
