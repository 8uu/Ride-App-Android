package com.example.rideapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.rideapp.Database.Model.Feedback;

import java.util.List;


public class FeedbackAdapter extends ArrayAdapter<Feedback> {
    private  int idResursa;

    public FeedbackAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        idResursa = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Feedback feedback = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(idResursa, null);

        TextView tv = v.findViewById(R.id.item_feed);
        tv.setText(feedback.getEmail() + ": " + feedback.getText());

        return v;
    }
}
