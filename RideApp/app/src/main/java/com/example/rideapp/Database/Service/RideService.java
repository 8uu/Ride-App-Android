package com.example.rideapp.Database.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.example.rideapp.Database.DAO.RideDAO;
import com.example.rideapp.Database.DatabaseManager;
import com.example.rideapp.Database.Model.Ride;

import java.util.List;

public class RideService {
    private static RideDAO rideDAO;

    public static class GetRides extends AsyncTask<Void, Void, List<Ride>> {
        public GetRides(Context context) {
            rideDAO = DatabaseManager.getInstance(context).getRideDAO();
        }

        @Override
        protected List<Ride> doInBackground(Void... voids) {
            return rideDAO.getRides();
        }
    }

    public static class GetFilteredRides extends AsyncTask<String, Void, List<Ride>> {
        public GetFilteredRides(Context context) {
            rideDAO = DatabaseManager.getInstance(context).getRideDAO();
        }

        @Override
        protected List<Ride> doInBackground(String... strings) {
            if(strings == null || strings.length != 2) {
                return null;
            }
            String locPlecare = strings[0];
            String locSosire = strings[1];
            return rideDAO.getFilteredRides(locPlecare, locSosire);
        }
    }

    public static class Insert extends AsyncTask<Ride, Void, Ride> {
        public Insert(Context context) {
            rideDAO = DatabaseManager.getInstance(context).getRideDAO();
        }

        @Override
        protected Ride doInBackground(Ride... rides) {
            if (rides == null || rides.length != 1) {
                return null;
            }
            Ride ride = rides[0];
            long id = rideDAO.insert(ride);
            if (id != -1) {
                ride.setId(id);
                return ride;
            }
            return null;
        }
    }

}
