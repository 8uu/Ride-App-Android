package com.example.rideapp.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rideapp.Classes.DatabaseRideApp;
import com.example.rideapp.Classes.User;
import com.example.rideapp.MainActivity;
import com.example.rideapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.net.InetAddress;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private User utilizator;
    private TextInputEditText tiet_email;
    private TextInputEditText tiet_pass;
    private DatabaseRideApp db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseRideApp(this);


        CardView login = findViewById(R.id.card_login);
        login.setOnClickListener(this);
        Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        tiet_email = findViewById(R.id.tiet_email);
        tiet_pass = findViewById(R.id.tiet_password);
        String email = tiet_email.getText().toString();
        String pass = tiet_pass.getText().toString();

        if(email.matches("") && pass.matches("")) {
            Toast.makeText(getApplicationContext(), "Introdu datele de logare!", Toast.LENGTH_LONG).show();
        } else {
            Cursor response = db.checkUser(email, pass);
            if(response.getCount() == 0) {
                Toast.makeText(getApplicationContext(), "Date de logare incorecte!", Toast.LENGTH_LONG).show();
            } else {
                while (response.moveToNext()) {
                    utilizator = new User (response.getString(1), response.getString(0),
                            response.getString(2), response.getString(3));
                }
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("utilizator", utilizator);
                startActivity(intent);
            }
        }
    }


}
