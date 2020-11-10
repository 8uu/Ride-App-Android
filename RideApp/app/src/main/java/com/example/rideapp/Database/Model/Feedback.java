package com.example.rideapp.Database.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Feedbacks",  foreignKeys = {
        @ForeignKey(entity = Ride.class,
        parentColumns = "id",
        childColumns = "idRide")
})
public class Feedback {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idFeed")
    private long idFeed;
    @ColumnInfo(name = "text")
    private String text;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "idRide")
    private long idRide;

    @Ignore
    public Feedback() {
    }

    public Feedback(long idFeed, String text, String email, long idRide) {
        this.idFeed = idFeed;
        this.text = text;
        this.email = email;
        this.idRide = idRide;
    }

    public long getIdFeed() {
        return idFeed;
    }

    public void setIdFeed(long idFeed) {
        this.idFeed = idFeed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdRide() {
        return idRide;
    }

    public void setIdRide(long idRide) {
        this.idRide = idRide;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "idFeed=" + idFeed +
                ", text='" + text + '\'' +
                '}';
    }
}
