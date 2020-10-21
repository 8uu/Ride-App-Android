package com.example.rideapp.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String nume;
    private String email;
    private String telefon;
    private String parola;

    public User() {

    }

    public User(String nume, String email, String telefon, String parola) {
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
        this.parola = parola;
    }

    protected User(Parcel in) {
        nume = in.readString();
        email = in.readString();
        telefon = in.readString();
        parola = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(email);
        dest.writeString(telefon);
        dest.writeString(parola);
    }
}
