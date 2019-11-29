package ca.mcgill.ecse321.tutoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class StudentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        getSupportActionBar().setTitle("Students");

        HttpUtils.get("/students", new RequestParams(), new GetStudentsResponseHandler((TableLayout) findViewById(R.id.table)));

    }

    private void addStudent(TableLayout table, String username, String first, String last, boolean active)
    {
        TableRow row = new TableRow(this);
        for (String data : new String[]
                {
                        truncate(username, 15),
                        truncate(first, 10),
                        truncate(last, 10)
                })
        {
            TextView text = new TextView(this);
            text.setTextSize(16);
            text.setText(data);
            text.setPadding(0,15,0,0);
            row.addView(text);
        }
        CheckBox activeCheckBox = new CheckBox(this);
        activeCheckBox.setChecked(active);
        activeCheckBox.setOnCheckedChangeListener(new OnStudentCheckChanged(username));
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

    private class OnStudentCheckChanged implements OnCheckedChangeListener
    {
        private String username;

        public OnStudentCheckChanged(String username) { this.username = username; }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            HttpUtils.post("/students/" + username + "/" + (isChecked ? "reactivate" : "deactivate"),
                    new RequestParams(), new JsonHttpResponseHandler()
                    {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
                        {
                            StudentsActivity.this.getSupportActionBar().setTitle("Error changing " + username + " status: " + throwable.getMessage());
                        }
                    });
        }
    }
}
