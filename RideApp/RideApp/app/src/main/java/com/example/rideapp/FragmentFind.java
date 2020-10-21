package com.example.rideapp;


import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rideapp.Classes.DatabaseRideApp;
import com.example.rideapp.Classes.Ride;
import com.example.rideapp.Classes.RideAdapter;
import com.example.rideapp.Network.HttpManager;
import com.example.rideapp.Network.HttpResponse;
import com.example.rideapp.Network.Item;
import com.example.rideapp.Network.JsonParser;
import com.example.rideapp.Network.Localitati;
import com.example.rideapp.Network.Strazi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFind extends Fragment {

    private CheckBox checkBox;
    private AutoCompleteTextView loc_plecare;
    private AutoCompleteTextView destinatie;

    private static final String URL = "https://api.myjson.com/bins/1ema6q";
    private HttpResponse httpResponse;
    private List<Item> selectedResponse = new ArrayList<>();
    DatabaseRideApp db;
    List<Ride> lista;
    ListView listView;
    Button btn;


    public FragmentFind() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_find, container, false);
        checkBox = fragmentView.findViewById(R.id.check_frecventare);
        loc_plecare = fragmentView.findViewById(R.id.loc_plecare_find);
        destinatie = fragmentView.findViewById(R.id.destinatie_find);
        listView = fragmentView.findViewById(R.id.listview_rides);
        btn = fragmentView.findViewById(R.id.buttonSearch);

        new HttpManager() {
            @Override
            protected void onPostExecute(String s) {
                httpResponse = JsonParser.parseJson(s);
                selectedResponse = httpResponse.getJudete();

                ArrayList<String> listaAdrese = new ArrayList<>();
                for(Item judet : selectedResponse) {
                    String jud = judet.getNume();
                    jud += ", ";
                    jud += judet.getPrescurtare();
                    jud += ", ";
                    for(Localitati localitate : judet.getLocalitati()) {
                        String loc = localitate.getNume();
                        loc += ", ";
                        for(Strazi strada : localitate.getStrazi()) {
                            String str = strada.getNume();
                            str += ", ";
                            str += strada.getNumar();
                            listaAdrese.add(jud + loc + str);
                        }
                    }
                }

                ArrayAdapter<String> adapterOrase = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, listaAdrese);
                loc_plecare.setAdapter(adapterOrase);
                destinatie.setAdapter(adapterOrase);
            }
        }.execute(URL);

        SharedPreferences locations = Objects.requireNonNull(this.getActivity()).getSharedPreferences("LocationPrefs", 0);
        final SharedPreferences.Editor editor = locations.edit();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    if (loc_plecare.getText().toString().matches("") && destinatie.getText().toString().matches("")) {
                        Toast.makeText(getContext(), "Introdu datele pentru a le putea salva!", Toast.LENGTH_LONG).show();
                        checkBox.setChecked(false);
                    } else {
                        editor.putString("loc_plecare", loc_plecare.getText().toString());
                        editor.putString("destinatie", destinatie.getText().toString());
                        editor.apply();
                    }
                }
            }
        });

        SharedPreferences locationsPref = this.getActivity().getSharedPreferences("LocationPrefs", 0);
        String prefDestinatie = locationsPref.getString("destinatie", null);
        String prefLoc_Plecare = locationsPref.getString("loc_plecare", null);
        if (prefDestinatie != null && prefLoc_Plecare != null) {
            destinatie.setText(prefDestinatie);
            loc_plecare.setText(prefLoc_Plecare);
        }

        lista = new ArrayList<>();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loc_plecare.getText().toString().matches("") && !destinatie.getText().toString().matches("")) {
                    String adresaPlecare = loc_plecare.getText().toString()
                            .substring(loc_plecare.getText().toString().indexOf(", ") + 2,
                                    loc_plecare.getText().toString().lastIndexOf(", "));
                    String adresaSosire = destinatie.getText().toString()
                            .substring(destinatie.getText().toString().indexOf(", ") + 2,
                                    destinatie.getText().toString().lastIndexOf(", "));

                    db = new DatabaseRideApp(v.getContext());
                    Cursor response = db.getLocations(adresaSosire, adresaPlecare);

                    if(response.getCount() == 0) {
                        Toast.makeText(getContext(), "Nu s-au gasit curse!", Toast.LENGTH_LONG).show();
                    }
                    while (response.moveToNext()) {
                        Ride listItem = new Ride (response.getString(1), response.getString(2),
                                response.getString(3), response.getFloat(4),
                                response.getString(5), response.getInt(6));
                        lista.add(listItem);
                    }

                    RideAdapter rideAdapter = new RideAdapter(getContext(),R.layout.item_layout, lista);
                    listView.setAdapter(rideAdapter);

                }
            }
        });

        return fragmentView;
    }

}
