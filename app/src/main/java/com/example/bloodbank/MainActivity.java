package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        signup = (Button) findViewById(R.id.buttonSignUp);
        signup.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Context context = MainActivity.this;
                Class destinationActivity = SignUp.class;
                Intent startSignUpIntent = new Intent(context, destinationActivity);
                startActivity(startSignUpIntent);
            }
        });
    }}