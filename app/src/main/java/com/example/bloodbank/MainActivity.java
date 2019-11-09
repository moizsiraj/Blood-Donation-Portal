package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        login = (Button) findViewById(R.id.signout);
        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Context context = MainActivity.this;
                Class destinationActivity = userPage.class;
                Intent startReqDonIntent = new Intent(context, destinationActivity);
                startActivity(startReqDonIntent);
            }
        });
    }
}