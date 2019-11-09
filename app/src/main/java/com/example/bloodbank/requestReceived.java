package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class requestReceived extends AppCompatActivity {
    private RecyclerView recyclerView;
    private requestAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<requestCardItems> requests;
    private TextView empty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_received);


        //create items to be on the view here
        createRequestList();
        buildRecyclerView();
        checkEmpty();
    }

    public void removeItem(int position){
        requests.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void createRequestList(){
        requests = new ArrayList<>();
        requests.add(new requestCardItems("Maaz Masood","Agha Khan Hosp.","Main","26/10/2019","16:21:22"));
        requests.add(new requestCardItems("Mirza Abbas","National Medical Hosp.","DHA Phase 1","26/10/2019","16:21:22"));
        requests.add(new requestCardItems("Moiz Siraj","National Medical Hosp.","DHA Phase 1","26/10/2019","16:21:22"));
    }

    public void buildRecyclerView(){
        empty = findViewById(R.id.norequest);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new requestAdapter(requests);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new requestAdapter.OnItemClickListener() {

            @Override
            public void onAcceptClick(int position) {
                        Context context = requestReceived.this;
                        Class destinationActivity = acceptScreen.class;
                        Intent startIntent = new Intent(context, destinationActivity);
                        startActivity(startIntent);
                removeItem(position);
                checkEmpty();
            }

            @Override
            public void onRejectClick(int position) {
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
