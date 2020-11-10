package com.example.rideapp.Database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rideapp.Database.DAO.FeedbackDAO;
import com.example.rideapp.Database.DAO.RideDAO;
import com.example.rideapp.Database.Model.Feedback;
import com.example.rideapp.Database.Model.Ride;

@Database(entities = {Ride.class, Feedback.class},
        exportSchema = false,
        version = 5)
public abstract class DatabaseManager
        extends RoomDatabase {
    private static final String DB_NAME = "rideshare_db";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(
            Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room
                            .databaseBuilder(context,
                                    DatabaseManager.class,
                                    DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                    return databaseManager;
                }
            }
        }
        return databaseManager;
    }

    public abstract RideDAO getRideDAO();
    public abstract FeedbackDAO getFeedbackDAO();

}
