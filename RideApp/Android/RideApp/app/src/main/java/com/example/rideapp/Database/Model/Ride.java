package com.example.rideapp.Database.Model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Rides")
public class Ride implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "locPlecare")
    private String loc_plecare;
    @ColumnInfo(name = "destinatie")
    private String destinatie;
    @ColumnInfo(name = "nrLocuri")
    private String nr_locuri;
    @ColumnInfo(name = "pret")
    private float pret;
    @ColumnInfo(name = "data")
    private String data;
    @ColumnInfo(name = "locBagaj")
    private int loc_bagaj;

    @Ignore
    public Ride() {
    }

    public Ride(long id, String loc_plecare, String destinatie, String nr_locuri, float pret, String data, int loc_bagaj) {
        this.id = id;
        this.loc_plecare = loc_plecare;
        this.destinatie = destinatie;
        this.nr_locuri = nr_locuri;
        this.pret = pret;
        this.data = data;
        this.loc_bagaj = loc_bagaj;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoc_plecare() {
        return loc_plecare;
    }

    public void setLoc_plecare(String loc_plecare) {
        this.loc_plecare = loc_plecare;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public String getNr_locuri() {
        return nr_locuri;
    }

    public void setNr_locuri(String nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLoc_bagaj() {
        return loc_bagaj;
    }

    public String getLocBagaj() {
        if(loc_bagaj == 0){
            return "NU";
        } else {
            return "DA";
        }
    }

    public void setLoc_bagaj(int loc_bagaj) {
        this.loc_bagaj = loc_bagaj;
    }

    @Override
    public String toString() {
        return "Cod calatorie: " + id +
                "\n\nLoc Plecare: " + loc_plecare +
                "\n\nDestinatie: " + destinatie +
                "\nNr. locuri: " + nr_locuri +
                "\n\nPret: " + pret + " RON" +
                "\n\nData: " + data +
                "\n\nLoc bagaj: " + getLocBagaj();
    }

    protected Ride(Parcel in) {
        id = in.readLong();
        loc_plecare = in.readString();
        destinatie = in.readString();
        nr_locuri = in.readString();
        pret = in.readFloat();
        data = in.readString();
        loc_bagaj = in.readInt();
    }

    public static final Creator<Ride> CREATOR = new Creator<Ride>() {
        @Override
        public Ride createFromParcel(Parcel in) {
            return new Ride(in);
        }

        @Override
        public Ride[] newArray(int size) {
            return new Ride[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(loc_plecare);
        dest.writeString(destinatie);
        dest.writeString(nr_locuri);
        dest.writeFloat(pret);
        dest.writeString(data);
        dest.writeInt(loc_bagaj);
    }
}
