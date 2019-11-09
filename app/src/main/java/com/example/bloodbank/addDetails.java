package com.example.bloodbank;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class addDetails extends AppCompatActivity {

    Spinner signupgender;
    EditText signupdate;
    Calendar dob = Calendar.getInstance();
    private FirebaseDatabase database;
    private DatabaseReference userDetails;
    private ChildEventListener mChildEventListener;
    private EditText name;
    private EditText phone;
    private EditText DoB;
    private Spinner gender;
    private Spinner bloodGrp;
    private Spinner location;
    private String Sname;
    private String Sphone;
    private String SDoB;
    private String Sgender;
    private String SbloodGrp;
    private String Slocation;
    private Button save;
    private user u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_details);
        setDate();
        database = FirebaseDatabase.getInstance();
        userDetails = database.getReference().child("users");

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                getData();
                u = new user(Sname, Sphone, SDoB, Sgender, SbloodGrp, Slocation);
                userDetails.push().setValue(u);
                Context context = addDetails.this;
                Class destinationActivity = userPage.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
                finish();
            }
        });
    }



    private void setDate(){
        signupdate = (EditText) findViewById(R.id.signupdate);
        signupdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                dob = Calendar.getInstance();
                int mYear = dob.get(Calendar.YEAR);
                int mMonth = dob.get(Calendar.MONTH);
                int mDay = dob.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog( addDetails.
                        this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        dob.set(selectedyear,selectedmonth,selectedday);
                        updateLabel();
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        signupdate.setText(sdf.format(dob.getTime()));
    }

    private void getData(){
        name = findViewById(R.id.signupname);
        Sname = name.getText().toString();
        phone = findViewById(R.id.signupno);
        Sphone = phone.getText().toString();
        DoB = findViewById(R.id.signupdate);
        SDoB = DoB.getText().toString();
        gender = findViewById(R.id.signupgender);
        Sgender = gender.getSelectedItem().toString();
        bloodGrp = findViewById(R.id.signupbloodgrp);
        SbloodGrp = bloodGrp.getSelectedItem().toString();;
        location = findViewById(R.id.signuplocation);
        Slocation = location.getSelectedItem().toString();;
    }
}
