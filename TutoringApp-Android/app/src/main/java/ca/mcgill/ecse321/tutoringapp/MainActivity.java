package ca.mcgill.ecse321.tutoringapp;

import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static String loginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getStringExtra(Login.USERNAME) != null)
            loginName = getIntent().getStringExtra(Login.USERNAME);
        getSupportActionBar().setTitle("Welcome " + loginName + "!");
    }

    public void studentsClicked(View view)
        { gotoActivity(StudentsActivity.class); }

    private void gotoActivity(Class<? extends AppCompatActivity> a)
    {
        startActivity(new Intent(this, a));
    }
}
