package ca.mcgill.ecse321.tutoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity
{
    public static String USERNAME = "ca.mcgill.ecse321.tutoringapp.USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signIn(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USERNAME, ((EditText)findViewById(R.id.username)).getText().toString());
        startActivity(intent);
    }
}
