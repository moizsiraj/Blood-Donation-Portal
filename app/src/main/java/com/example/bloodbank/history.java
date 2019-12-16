package com.example.bloodbank;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class history extends AppCompatActivity {

    TextView rr;
    TextView ra;
    TextView rrej;
    TextView rs;
    long RR;
    long RA;
    long RREJ;
    long RS;
    String uid;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    DatabaseReference db;
    ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rr = findViewById(R.id.rrTxt);
        ra = findViewById(R.id.raTxt);
        rrej = findViewById(R.id.rjTxt);
        rs = findViewById(R.id.rsTxt);

        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        db = database.getReference().child("Requests").child(uid).child("requestReceived");
        Log.d("checkxxx", db.toString());
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RR = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        db.addValueEventListener(valueEventListener);

        db = database.getReference().child("Requests").child(uid).child("requestAccepted");
        Log.d("checkxxx", db.toString());
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RA = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        db.addValueEventListener(valueEventListener);

        db = database.getReference().child("Requests").child(uid).child("requestRejected");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RREJ = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        db.addValueEventListener(valueEventListener);

        db = database.getReference().child("Requests").child(uid).child("requestSent");
        Log.d("checkxxx", db.toString());
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Log.d("checkxxx", child.toString());

                    RS++;
                    }
                //RS = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        db.addValueEventListener(valueEventListener);

        Log.d("checkxxx", String.valueOf(RR + RA + RREJ + RS));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RR = RR+RA+RREJ;
                rr.setText(String.valueOf((RR)));
                ra.setText(String.valueOf((RA)));
                rrej.setText(String.valueOf((RREJ)));
                rs.setText(String.valueOf((RS)));
            }
        }, 7500);

    }
}
