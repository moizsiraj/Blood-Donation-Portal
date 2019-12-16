package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class requestReceived extends AppCompatActivity {
    private RecyclerView recyclerView;
    private requestAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<requestCardItems> requests;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;
    private TextView empty;
    private String uid;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_received);
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        createRequestList();
        //create items to be on the view here
        //buildRecyclerView();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkEmpty();
            }
        }, 15000);

    }

    public void removeItem(int position){
        requests.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void createRequestList(){
        requests = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference().child("Requests").child(uid).child("requestReceived");
        Log.d("requestReceivedxxx", "ref" + dbRef.toString());
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("requestReceivedxxx", String.valueOf(dataSnapshot.exists()));
                Log.d("requestReceivedxxx", dataSnapshot.toString());
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    requestSent value = child.getValue(requestSent.class);
                    String name = value.getName();
                    String bloodbank = value.getBloodbank();
                    String branch = value.getLocation();
                    String date = value.getDate();
                    String time = value.getTime();
                    String phone = value.getPhone();
                    String key = value.getKey();
                    Log.d("requestReceivedxxx", value.toString());
                    requests.add(new requestCardItems(name,bloodbank,branch, date, time, phone, key));
                }
                buildRecyclerView();
                Log.d("requestReceivedxxx", requests.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dbRef.addValueEventListener(valueEventListener);
        Log.d("recyclerCheck", "at the end of createlist");
    }

    public void buildRecyclerView(){
        empty = findViewById(R.id.norequest);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        Log.d("recyclerCheck", "at the start of createlist");
        Log.d("recyclerCheck", "list is sent");
        adapter = new requestAdapter(requests);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new requestAdapter.OnItemClickListener() {

            @Override
            public void onAcceptClick(int position) {
                        Context context = requestReceived.this;
                        Class destinationActivity = acceptScreen.class;
                        Intent startIntent = new Intent(context, destinationActivity);
                        requestCardItems currentCard = requests.get(position);
                        DatabaseReference dbRA = FirebaseDatabase.getInstance().getReference().child("Requests").child(uid).child("requestAccepted");
                        dbRA.child(currentCard.getKey()).setValue(currentCard);
                        DatabaseReference dbRR = FirebaseDatabase.getInstance().getReference().child("Requests").child(uid).child("requestReceived");
                        dbRR.child(currentCard.getKey()).removeValue();
                        String name = currentCard.getName();
                        String phone = currentCard.getPhone();
                        String bloodbank = currentCard.getBloodBank();
                        String branch = currentCard.getBranch();
                        String date = currentCard.getDate();
                        String time = currentCard.getTime();
                        startIntent.putExtra("name", name);
                        startIntent.putExtra("phone", phone);
                        startIntent.putExtra("bloodbank", bloodbank);
                        startIntent.putExtra("branch", branch);
                        startIntent.putExtra("date", date);
                        startIntent.putExtra("time", time);
                        Log.d("userDetails", name + "," + phone ); //Don't ignore errors!
                        startActivity(startIntent);
                removeItem(position);
                checkEmpty();
            }

            @Override
            public void onRejectClick(int position) {
                requestCardItems currentCard = requests.get(position);
                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Requests").child(uid).child("requestRejected");
                db.child(currentCard.getKey()).setValue(currentCard);
                DatabaseReference dbRR = FirebaseDatabase.getInstance().getReference().child("Requests").child(uid).child("requestReceived");
                dbRR.child(currentCard.getKey()).removeValue();
                removeItem(position);
                checkEmpty();
            }
        });
    }

    public void checkEmpty(){
        if (requests.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }
    }


}
