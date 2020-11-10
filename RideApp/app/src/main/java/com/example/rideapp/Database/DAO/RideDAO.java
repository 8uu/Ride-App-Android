package com.example.rideapp.Database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.rideapp.Database.Model.Ride;
import java.util.List;

@Dao
public interface RideDAO {
    @Query("SELECT * FROM rides")
    List<Ride> getRides();

    @Query("SELECT * FROM rides WHERE locPlecare LIKE  '%' || :adresaPlecare || '%' " +
            "AND destinatie LIKE '%' || :adresaSosire || '%'")
    List<Ride> getFilteredRides(String adresaPlecare, String adresaSosire);

    @Insert
    long insert(Ride ride);
}
