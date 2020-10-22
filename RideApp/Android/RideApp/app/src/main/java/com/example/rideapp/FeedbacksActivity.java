package com.example.rideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rideapp.Classes.RideAdapter;
import com.example.rideapp.Database.Model.Feedback;
import com.example.rideapp.Database.Model.Ride;
import com.example.rideapp.Database.Service.FeedbackService;

import java.util.ArrayList;
import java.util.List;

public class FeedbacksActivity extends AppCompatActivity {

    private List<Feedback> lista = new ArrayList<>();
    ListView lv;
    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);

        lv = findViewById(R.id.feedbacks_rides);
        et = findViewById(R.id.feedback_et);
        btn = findViewById(R.id.trimite_btn);

        Intent intent = getIntent();
        final Ride ride = intent.getParcelableExtra("ride");
        getFeedbacksFromDb();
        updateLista();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feedback feedback = new Feedback();
                if(!et.getText().toString().matches("")) {
                    feedback.setText(et.getText().toString());
                    feedback.setEmail(FragmentHome.utilizator.getEmail());
                    feedback.setIdRide(ride.getId());
                    et.setText("");
                    inserareFeedbackInDb(feedback);
                } else {
                    Toast.makeText(getApplicationContext(), "Mesaj necompletat!", Toast.LENGTH_LONG).show();
                }
                getFeedbacksFromDb();
                updateLista();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void getFeedbacksFromDb() {
        new FeedbackService.GetFeedbacks(getApplicationContext()) {
            @Override
            protected void onPostExecute(List<Feedback> feedbacks) {
                if (feedbacks != null) {
                    lista.clear();
                    lista.addAll(feedbacks);
                    updateLista();
                } else {
                    Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    private void inserareFeedbackInDb(Feedback feedback) {
        new FeedbackService.Insert(getApplicationContext()).execute(feedback);
    }

    private void updateLista() {
        FeedbackAdapter feedbackAdapter = new FeedbackAdapter(getApplicationContext(),R.layout.feedback_layout, lista);
        lv.setAdapter(feedbackAdapter);
    }
}
