package com.example.rideapp.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.rideapp.Database.Model.Ride;
import com.example.rideapp.FeedbacksActivity;
import com.example.rideapp.FragmentHome;
import com.example.rideapp.R;
import com.example.rideapp.RideDetailsActivity;
import java.util.List;

public class RideAdapter extends ArrayAdapter<Ride> {

    private  int idResursa;

    public RideAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        idResursa = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Ride ride = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(idResursa, null);

        TextView plecareDestinatie = v.findViewById(R.id.tv_plecare_destinatie);
        TextView data = v.findViewById(R.id.tv_item_data);
        Button btn = v.findViewById(R.id.item_btn);
        Button btnMsj = v.findViewById(R.id.mesaj_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RideDetailsActivity.class);
                intent.putExtra("ride", ride);
                getContext().startActivity(intent);
            }
        });

        btnMsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedbacksActivity.class);
                intent.putExtra("ride", ride);
                getContext().startActivity(intent);
            }
        });

        plecareDestinatie.setText(ride.getLoc_plecare() + " - " + ride.getDestinatie());
        data.setText(ride.getData());

        return v;
    }
}
