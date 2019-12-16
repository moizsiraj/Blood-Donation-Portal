package com.example.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Arrays;
import java.util.Objects;

public class userPage extends AppCompatActivity {

    private Button request;
    private Button checkRequest;
    private Button details;
    private Button signout;
    private Button history;
    private String uid;
    private boolean setDetails;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private AuthMethodPickerLayout authMethodPickerLayout;
    private FirebaseDatabase database;
    private DatabaseReference userDetails;
    private ChildEventListener mChildEventListener;
    private String message;
    private TextView messageBox;
    private boolean check;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
        //Toast.makeText(this,"Click ADD DETAILS to input your information and use the app!",Toast.LENGTH_LONG).show();
        firebaseAuth = FirebaseAuth.getInstance();
        details = (Button) findViewById(R.id.buttonDetails);
        details.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Context context = userPage.this;
                Class destinationActivity = addDetails.class;
                Intent startSignUpIntent = new Intent(context, destinationActivity);
                startActivity(startSignUpIntent);
            }
        });

        signout = (Button) findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(userPage.this);
            }
        });

        request = (Button) findViewById(R.id.buttonRequestPage);
        request.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Context context = userPage.this;
                Class destinationActivity = reqPage.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        checkRequest = (Button) findViewById(R.id.buttonDonate);
        checkRequest.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Context context = userPage.this;
                Class destinationActivity = requestReceived.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        history = findViewById(R.id.buttonHistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = userPage.this;
                Class destinationActivity = history.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });


        authMethodPickerLayout = new AuthMethodPickerLayout
                .Builder(R.layout.login_page)
                .setGoogleButtonId(R.id.google_signin)
                .setPhoneButtonId(R.id.email_signin)
                // ...
                //.setTosAndPrivacyPolicyId(R.id.baz)
                .build();

        authStateListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    /*Context context = userPage.this;
                    Class destinationActivity = userPage.class;
                    Intent startIntent = new Intent(context, destinationActivity);
                    startActivity(startIntent);*/
                }
                else{
                    startActivityForResult(
                            // You must provide a custom layout XML resource and configure at least one
                            // provider button ID. It's important that that you set the button ID for every provider
                            // that you have enabled.

                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setLogo(R.drawable.icon)
                                    .setIsSmartLockEnabled(false)//allows  for saving credentials for login//
                                    .setAuthMethodPickerLayout(authMethodPickerLayout)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.PhoneBuilder().build()))
                                            //new AuthUI.IdpConfig.EmailBuilder().build()))

                                    .build(),
                            RC_SIGN_IN);
                }

            }
        };
        messageBox = findViewById(R.id.notifBox);
       // createSetMessage();
         //chkDetails();
    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
        //chkDetails();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                Toast.makeText(this,"Signed In!",Toast.LENGTH_SHORT).show();
                chkDetails();
            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Signed In Canceled",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    void createSetMessage(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        final DatabaseReference userRef = db.getReference().child("userDetails");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Log.d("setMessage", "chalta hai");
                for (DataSnapshot child : children) {
                    userDetails value = child.getValue(userDetails.class);
                    Log.d("setMessage", value.getUid() + "," + uid);
                    if ((value.getUid()).equals(uid)){
                        message = "Hi, " + value.getName();
                        Log.d("setMessage", message);
                    }
                }
                messageBox.setText(message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        userRef.addValueEventListener(valueEventListener);
    }

    private void chkDetails() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        check = false;
        final DatabaseReference userRef = db.getReference().child("userDetails");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Log.d("checkxxx before for", String.valueOf(check));
                for (DataSnapshot child : children) {
                    userDetails value = child.getValue(userDetails.class);
                    if ((value.getUid()).equals(uid)){
                        Log.d("checkxxx", value.getUid() + "," + uid);
                        check = true;
                    }
                }
                Log.d("checkxxx after for", String.valueOf(check));

                Log.d("checkxxx before return", String.valueOf(check));

                if (check == false){
                    Context context = userPage.this;
                    Class destinationActivity = addDetails.class;
                    Intent startSignUpIntent = new Intent(context, destinationActivity);
                    Toast.makeText(userPage.this, "Please enter your details", Toast.LENGTH_LONG);
                    startActivity(startSignUpIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        userRef.addValueEventListener(valueEventListener);
        Toast.makeText(userPage.this, "Please input your information first", Toast.LENGTH_LONG);
    }

}
