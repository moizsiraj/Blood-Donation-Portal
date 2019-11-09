package com.example.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class userPage extends AppCompatActivity {

    private Button request;
    private Button checkRequest;
    private Button details;
    private Button signout;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private AuthMethodPickerLayout authMethodPickerLayout;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

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

        request = (Button) findViewById(R.id.buttonRequest);
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


        authMethodPickerLayout = new AuthMethodPickerLayout
                .Builder(R.layout.login_page)
                .setGoogleButtonId(R.id.google_signin)
                .setEmailButtonId(R.id.email_signin)
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
                                    .setIsSmartLockEnabled(false)//allows  for saving credentials for login//
                                    .setAuthMethodPickerLayout(authMethodPickerLayout)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build())).build(),
                            RC_SIGN_IN);
                }

            }
        };

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                Toast.makeText(this,"Signed In!",Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Signed In Canceled",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
