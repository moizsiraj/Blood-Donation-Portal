package com.example.bloodbank;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class reqPage extends AppCompatActivity {

    private Spinner bloodBank;
    private Spinner location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);
        bloodBank = findViewById(R.id.requestbloodbank);
        location = findViewById(R.id.requestlocation);


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
    }
