package com.example.rideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rideapp.Classes.Ride;

public class RideDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);

        Intent intent = getIntent();
        Ride ride = intent.getParcelableExtra("ride");

        TextView textView = findViewById(R.id.tv_details);
        textView.setText(ride.toString());
    }
}
