package com.example.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class reqPage extends AppCompatActivity {

    private TextView noOfDonors;
    private Spinner bloodGrp;
    private Spinner bloodBank;
    private Spinner location;
    private Button requestBtn;
    private requestSent Request;
    private String uid;
    private String name;
    private String phone;
    private String SnoOfDonor;
    private String SbloodGrp;
    private String SbloodBank;
    private String Slocation;
    private String date;
    private String time;
    private String key;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;
    private ArrayList<userDetails> datafetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);
        datafetch = new ArrayList<>();
        getData();
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();
                database = FirebaseDatabase.getInstance();
                setData();
                createList();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                Request = new requestSent(SnoOfDonor,SbloodGrp, SbloodBank, Slocation, date, time, key, name, phone);
                dbRef = database.getReference().child("Requests/"+ Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
                dbRef.child("requestSent").child(key).setValue(Request);
                Toast.makeText(reqPage.this,"Request Sent",Toast.LENGTH_SHORT).show();
                        sendRequest();
                    }
                }, 7500);
                finish();
            }
        });

        bloodBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {

                if (bloodBank.getSelectedItem().equals("Afzaal Memorial Thalassemia Foundation (AMTF)")) {
                        // set adapter to spinner_2 here for "Red" selected
                        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                                reqPage.this, R.array.AMTF_location_arrays, android.R.layout.simple_spinner_item);
                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        location.setAdapter(adapter1);
                }
                if (bloodBank.getSelectedItem().equals("Aga Khan Lab")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.AKL_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }
                if (bloodBank.getSelectedItem().equals("AKU Aga Khan University Hospital Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.AKU_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Al-Raza Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.RBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Al-Syed Welfare Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.SWBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Anklesaria Laboratory & Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.ALBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Ar Rehman Blood Bank & Transfusion Services")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.RBBTS_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Blood Donation Center LNH")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.BDC_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Burhani Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.BBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Emergency Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.EBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Fatimid Foundation Thalassemia Centre")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.FFTC_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Husaini Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.HBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Jinnah Post Graduate Medical Center")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.JPFMC_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("JSMU Diagnostic Laboratory & Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.JSMU_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("JSMU Laboratory & Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.JSMUBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Kashif Iqbal Thalassemia Care Center")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.KITCC_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Liaquat National Hospital And Medical College")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.LNHMC_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals(">Liaquat National Hospital Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.LNHBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Liaquat National Hospital Laboratory Services")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.LNHLS_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Mohammadi Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.MBB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("NIBD Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.NIDB_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Patients Welfare Association")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.PWA_location_arrays, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Sahara Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.SBB_location_arrays
                            , android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Saylani Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.SYBB_location_arrays
                            , android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("The Indus Hospital Blood Centre")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.IHBB_location_arrays
                            , android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }if (bloodBank.getSelectedItem().equals("Voluntary Blood Bank Services")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.VBB_location_arrays
                            , android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }
                if (bloodBank.getSelectedItem().equals("Select Blood Bank")) {
                    // set adapter to spinner_2 here for "Red" selected
                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            reqPage.this, R.array.selectBloodBank, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    location.setAdapter(adapter1);
                }
            }});
        }

        void setData(){
            SnoOfDonor = noOfDonors.getText().toString();
            SbloodGrp = bloodGrp.getSelectedItem().toString();
            SbloodBank = bloodBank.getSelectedItem().toString();
            Slocation = location.getSelectedItem().toString();
            date = DateFormat.getDateInstance(DateFormat.SHORT).format(Calendar.getInstance().getTime());
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:00"));
            Date currentLocalTime = cal.getTime();
            DateFormat date = new SimpleDateFormat("HH:mm:ss a");
            date.setTimeZone(TimeZone.getTimeZone("GMT+5:00"));
            time = date.format(currentLocalTime);
            uid = firebaseAuth.getCurrentUser().getUid();
            key = uid + time;
        }

        void getData(){
            requestBtn = findViewById(R.id.buttonRequestPage);
            bloodBank = findViewById(R.id.requestbloodbank);
            noOfDonors = findViewById(R.id.noOfDonors);
            bloodGrp = findViewById(R.id.requestbloodgrp);
            location = findViewById(R.id.requestlocation);

        }

        void createList(){
        final DatabaseReference userRef = database.getReference().child("userDetails");
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    Log.d("createlist", userRef.toString());
                    for (DataSnapshot child : children) {
                        userDetails value = child.getValue(userDetails.class);
                        Log.d("createlist", value.toString());
                        Log.d("createlist", "chal raha hai for se pehlay tak");
                        Log.d("createlist", value.getName());
                        Log.d("createlist", Slocation + "," + value.getLocation());
                        Log.d("createlist", uid +"," + value.getUid());
                        if (Slocation.equals(value.getLocation()) && !value.getUid().equals(uid)){
                            datafetch.add(value);
                            Log.d("donor saved to list", value.getName());
                            Log.d("donor saved to list", datafetch.get(0).toString());
                        }
                        if (value.getUid().equals(uid)){
                            name = value.getName();
                            phone = value.getPhone();
                            Log.d("donor saved to list", name + "," + phone);
                        }
                    }
                    Log.d("sentRequest", "size at the end of createlist" + datafetch.size());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
        userRef.addValueEventListener(valueEventListener);
        }

        void sendRequest() {
            final DatabaseReference userRef = database.getReference().child("Requests");
            Log.d("sentRequest", userRef.toString());
            Log.d("sentRequest", "size at the start of sendRequest" + datafetch.size());
            int x = Integer.parseInt(SnoOfDonor);
            if (x  > datafetch.size()){
                x = datafetch.size();
            }
            for (int i = 0; i < x; i++) {
                DatabaseReference db = userRef.child(datafetch.get(i).uid);
                Log.d("sentRequest", db.toString());
                Log.d("uid found", db.toString());
                Log.d("sentRequest", String.valueOf(datafetch.size()));
                db.child("requestReceived").child(key).setValue(Request);
            }
        }

    }
