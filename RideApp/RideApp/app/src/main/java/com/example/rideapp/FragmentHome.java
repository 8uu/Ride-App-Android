package com.example.rideapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rideapp.Classes.DatabaseRideApp;
import com.example.rideapp.Classes.Ride;
import com.example.rideapp.Classes.RideAdapter;
import com.example.rideapp.Classes.User;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    private User utilizator;
    List<Ride> lista;
    DatabaseRideApp db;

    public FragmentHome() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView =  inflater.inflate(R.layout.fragment_home, container, false);

        db = new DatabaseRideApp(this.getContext());

        Intent intent = getActivity().getIntent();
        utilizator = intent.getParcelableExtra("utilizator");

        TextView tv = fragmentView.findViewById(R.id.tv_welcome);
        tv.setText(String.format("Bine ai venit, %s!", utilizator.getNume()));

        lista = new ArrayList<>();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Ride receivedRide = bundle.getParcelable("Ride");
            if(receivedRide != null) {
                db.insertData(receivedRide.getLoc_plecare(),
                        receivedRide.getDestinatie(),
                        receivedRide.getNr_locuri(),
                        receivedRide.getPret(),
                        receivedRide.getData(),
                        receivedRide.getLoc_bagaj(),
                        utilizator.getEmail());
            }
        }

        Cursor response = db.getData(utilizator.getEmail());
        while (response.moveToNext()) {
            Ride listItem = new Ride (response.getString(1), response.getString(2),
                    response.getString(3), response.getFloat(4),
                    response.getString(5), response.getInt(6));
            lista.add(listItem);
        }

        ListView lv = fragmentView.findViewById(R.id.listview_rides);
        RideAdapter rideAdapter = new RideAdapter(getContext(),R.layout.item_layout, lista);

        lv.setAdapter(rideAdapter);

        return fragmentView;
    }
}
