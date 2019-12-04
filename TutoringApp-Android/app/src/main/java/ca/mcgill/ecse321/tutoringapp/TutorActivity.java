package ca.mcgill.ecse321.tutoringapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Adapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TutorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);
        getSupportActionBar().setTitle("Tutors");

        HttpUtils.get("/tutors", new RequestParams(), new TutorActivity.GetTutorsResponseHandler((TableLayout) findViewById(R.id.table)));

    }



    private void addTutor(TableLayout table, String username, String first, String last, String status)
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
        ArrayList<String> status1 = new ArrayList<String>();
        status1.add("Pending");
        status1.add("Verified");
        status1.add("Terminated");
        Spinner spinner = (Spinner)findViewById(R.id.tutors);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, status1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        row.addView(spinner);
        table.addView(row);
    }

    private String truncate(String s, int l) { return s.length() > l ? s.substring(0,l-3) + "..." : s; }

    private class GetTutorsResponseHandler extends JsonHttpResponseHandler
    {
        private TableLayout table;
        public GetTutorsResponseHandler(TableLayout table) { this.table = table; }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response)
        {
            try
            {
                for (int i = 0; i < response.getJSONObject("_embedded").getJSONArray("tutors").length(); i++)
                {
                    JSONObject tutor = response.getJSONObject("_embedded").getJSONArray("tutors").getJSONObject(i);
                    String username = tutor.getJSONObject("_links").getJSONObject("self").getString("href");
                    username = username.substring(username.lastIndexOf('/') + 1);
                    addTutor(
                            table,
                            username,
                            tutor.getString("firstName"),
                            tutor.getString("lastName"),
                            tutor.getString("status"));
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
            TutorActivity.this.getSupportActionBar().setTitle("Error loading Tutors: " + throwable.getMessage());
        }
    }




}
