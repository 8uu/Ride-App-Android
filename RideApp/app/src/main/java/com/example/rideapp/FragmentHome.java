package com.example.rideapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rideapp.Database.Model.Ride;
import com.example.rideapp.Classes.RideAdapter;
import com.example.rideapp.Classes.User;
import com.example.rideapp.Database.Service.RideService;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    public static User utilizator;
    final List<Ride> lista = new ArrayList<>();
    ListView lv;
    public FragmentHome() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView =  inflater.inflate(R.layout.fragment_home, container, false);

        Intent intent = getActivity().getIntent();
        utilizator = intent.getParcelableExtra("utilizator");

        TextView tv = fragmentView.findViewById(R.id.tv_welcome);
        tv.setText(String.format("Bine ai venit, %s!", utilizator.getNume()));

        selectAllFromRidesDb();
        lv = fragmentView.findViewById(R.id.listview_rides);
        RideAdapter rideAdapter = new RideAdapter(getContext(),R.layout.item_layout, lista);
        lv.setAdapter(rideAdapter);
        registerForContextMenu(lv);

        return fragmentView;
    }

    @SuppressLint("StaticFieldLeak")
    private void selectAllFromRidesDb() {
        new RideService.GetRides(getContext()) {
            @Override
            protected void onPostExecute(List<Ride> rides) {
                if (rides != null) {
                    lista.clear();
                    lista.addAll(rides);
                } else {
                    Toast.makeText(getContext(), "empty", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                return true;
            case R.id.delete:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
