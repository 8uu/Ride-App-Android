package com.example.rideapp.Database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rideapp.Database.Model.Feedback;

import java.util.List;

@Dao
public interface FeedbackDAO {

    @Query("SELECT * FROM feedbacks")
    List<Feedback> getFeedbacks();

    @Insert
    long insert(Feedback feedback);
}
