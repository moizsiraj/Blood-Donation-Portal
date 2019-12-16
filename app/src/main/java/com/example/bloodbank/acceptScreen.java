package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class acceptScreen extends AppCompatActivity {

    Button call;
    Button direction;
    TextView phone;
    TextView name;
    TextView bloodbank;
    TextView branch;
    TextView date;
    TextView time;
    String Sname;
    String Sphone;
    String Sbloodbank;
    String Sbranch;
    String Sdate;
    String Stime;
    private FirebaseDatabase database;
    private DatabaseReference location;
    private ChildEventListener childEventListener;
    private String coordchk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_screen);
        getData();
        setData();
        call = findViewById(R.id.acceptRequest);
        direction = findViewById(R.id.rejectRequest);



        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                phone = findViewById(R.id.acceptScrNo);
                final String no = (String) phone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + no));//change the number
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request_page the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the userDetails grants the permission. See the documentation
                    // for Activity#requestPermissions for more userDetails.
                    return;
                }
                startActivity(callIntent);
            }
        });

        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                location = database.getReference().child("Blood Bank").child(Sbloodbank).child(Sbranch);
                Log.d("checkxxx", location.toString());

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                        for (DataSnapshot child : children) {
                            coordinates value = child.getValue(coordinates.class);
                            coordchk = value.getCoordinate();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                location.addValueEventListener(valueEventListener);
/*
                childEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        coordchk = dataSnapshot.getValue(coordinates.class);
                        Log.d("checkxxx", coordchk.getCoordinate());
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("checkyyy", coordchk.getCoordinate());
                    }
                };

                location.addChildEventListener(childEventListener);
*/


                final String link =  "http://maps.google.com/maps?saddr=&daddr=24.922286,67.102625";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(link));
                startActivity(intent);
            }
        });
        //http://maps.google.com/maps?saddr=&daddr=24.892242,67.074712
    }

    void getData() {
        //obtain  Intent Object send  from SenderActivity
        Intent intent = this.getIntent();
        /* Obtain String from Intent  */
        Sname = intent.getExtras().getString("name");
        Sbloodbank = intent.getExtras().getString("bloodbank");
        Sbranch = intent.getExtras().getString("branch");
        Sdate = intent.getExtras().getString("date");
        Stime = intent.getExtras().getString("time");
        Sphone = intent.getExtras().getString("phone");
        Log.d("userDetails received", Sname + "," + Sphone ); //Don't ignore errors!
    }

    void setData() {
        phone = findViewById(R.id.acceptScrNo);
        name = findViewById(R.id.acceptScrNameTxt);
        bloodbank = findViewById(R.id.acceptScrBloodBankTxt);
        branch = findViewById(R.id.acceptScrBranchTxt);
        date = findViewById(R.id.acceptScrDateTxt);
        time = findViewById(R.id.acceptScrTime);
        phone.setText(Sphone);
        name.setText(Sname);
        bloodbank.setText(Sbloodbank);
        branch.setText(Sbranch);
        date.setText(Sdate);
        time.setText(Stime);
    }
}
