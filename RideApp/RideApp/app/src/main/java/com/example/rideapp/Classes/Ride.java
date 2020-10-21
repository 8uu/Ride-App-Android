package com.example.rideapp.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Ride implements Parcelable {
    private String loc_plecare;
    private String destinatie;
    private String nr_locuri;
    private float pret;
    private String data;
    private int loc_bagaj;


    public Ride() {

    }

    public Ride(String loc_plecare, String destinatie, String nr_locuri, float pret, String data, int loc_bagaj) {
        this.loc_plecare = loc_plecare;
        this.destinatie = destinatie;
        this.nr_locuri = nr_locuri;
        this.pret = pret;
        this.data = data;
        this.loc_bagaj = loc_bagaj;
    }

    protected Ride(Parcel in) {
        loc_plecare = in.readString();
        destinatie = in.readString();
        nr_locuri = in.readString();
        pret = in.readFloat();
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

    public String isLoc_bagaj() {
        if(loc_bagaj == 1) {
            return "DA";
        } else {
            return "NU";
        }
    }

    public int getLoc_bagaj() {
        return loc_bagaj;
    }

    public void setLoc_bagaj(int loc_bagaj) {
        this.loc_bagaj = loc_bagaj;
    }

    @Override
    public String toString() {
        return "Loc plecare: " + loc_plecare + "\n\n" +
                "Destinatie: " + destinatie + "\n\n" +
                "Numar locuri: " + nr_locuri + "\n" +
                "Pret: " + pret + "\n" +
                "Data: " + data + "\n" +
                "Loc bagaj: " + isLoc_bagaj() ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loc_plecare);
        dest.writeString(destinatie);
        dest.writeString(nr_locuri);
        dest.writeFloat(pret);
        dest.writeInt(loc_bagaj);
    }
}
