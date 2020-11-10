package com.example.rideapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.example.rideapp.Database.Model.Ride;
import com.example.rideapp.Database.Service.RideService;
import com.example.rideapp.Network.HttpManager;
import com.example.rideapp.Network.HttpResponse;
import com.example.rideapp.Network.Item;
import com.example.rideapp.Network.JsonParser;
import com.example.rideapp.Network.Localitati;
import com.example.rideapp.Network.Strazi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class FragmentAdd extends Fragment implements DatePickerDialog.OnDateSetListener{

    private AutoCompleteTextView destinatie;
    private AutoCompleteTextView loc_plecare;
    private int day, month, year;
    private Spinner sp;
    private EditText et_pret;
    private Switch loc_bagaj;
    private Button data_button;
    private Ride ride;

    private static final String URL = "https://api.myjson.com/bins/1ema6q";
    private HttpResponse httpResponse;
    private List<Item> selectedResponse = new ArrayList<>();

    private String[] nr_locuri ={"unul","două","trei","patru","cinci"};


    public FragmentAdd() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        final View fragmentView = inflater.inflate(R.layout.fragment_add, container, false);
        sp = fragmentView.findViewById(R.id.spinner_locuri);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                android.R.layout.simple_spinner_dropdown_item, nr_locuri);
        sp.setAdapter(adapter);

        destinatie = fragmentView.findViewById(R.id.destinatie);
        loc_plecare = fragmentView.findViewById(R.id.loc_plecare);
        et_pret = fragmentView.findViewById(R.id.et_pret);
        data_button = fragmentView.findViewById(R.id.data_button);
        data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        loc_bagaj = fragmentView.findViewById(R.id.sw_locBagaj);


        Button next = fragmentView.findViewById(R.id.add);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loc_plecare.getText().toString().matches("") ||
                        destinatie.getText().toString().matches("") ||
                        et_pret.getText().toString().matches("") ||
                        data_button.getText().toString().matches("")) {
                    Toast.makeText(getActivity(), "Trebie sa completezi toate campurile!", Toast.LENGTH_LONG).show();
                } else {
                    ride = new Ride();
                    ride.setDestinatie(destinatie.getText().toString());
                    ride.setLoc_plecare(loc_plecare.getText().toString());
                    ride.setNr_locuri(sp.getSelectedItem().toString());
                    ride.setPret(Float.parseFloat(et_pret.getText().toString()));
                    ride.setData(data_button.getText().toString());

                    if(loc_bagaj.isChecked())
                        ride.setLoc_bagaj(1);
                    else
                        ride.setLoc_bagaj(0);

                    insertRideIntoDb(ride);
                }
            }
        });


        return fragmentView;
    }

    private void showDateDialog() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(
                this.getActivity(),this,year,day,month);
        dpd.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dpd.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        data_button.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
    }

    @SuppressLint("StaticFieldLeak")
    private void insertRideIntoDb(Ride ride) {
        new RideService.Insert(getContext()).execute(ride);
        Toast.makeText(getContext(), "S-a adaugat!", Toast.LENGTH_LONG).show();
    }
}
