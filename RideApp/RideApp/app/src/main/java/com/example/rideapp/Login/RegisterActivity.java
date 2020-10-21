package com.example.rideapp.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rideapp.Classes.DatabaseRideApp;
import com.example.rideapp.Classes.Ride;
import com.example.rideapp.Classes.User;
import com.example.rideapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseRideApp db;
    TextInputEditText nume;
    TextInputEditText email;
    TextInputEditText telefon;
    TextInputEditText pass1;
    TextInputEditText pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nume = findViewById(R.id.register_nume);
        email = findViewById(R.id.register_email);
        telefon = findViewById(R.id.register_tlf);
        pass1 = findViewById(R.id.register_pass1);
        pass2 = findViewById(R.id.register_pass2);

        CardView register = findViewById(R.id.card_register);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        db = new DatabaseRideApp(this);
        if(pass1.getText().toString().matches(pass2.getText().toString())) {
            User user = new User(nume.getText().toString(), email.getText().toString(), telefon.getText().toString(),pass1.getText().toString());
            db.insertUser(user);

            Toast.makeText(getApplicationContext(), "Te-ai inregistrat!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Parolele nu coincid!", Toast.LENGTH_LONG).show();
        }
    }
}
