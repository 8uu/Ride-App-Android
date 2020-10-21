package com.example.rideapp.Network;

import java.util.List;

public class Localitati {
    private String nume;
    private String diacritice;
    private List<Strazi> strazi;

    public Localitati(String nume, String diacritice, List<Strazi> strazi) {
        this.nume = nume;
        this.diacritice = diacritice;
        this.strazi = strazi;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDiacritice() {
        return diacritice;
    }

    public void setDiacritice(String diacritice) {
        this.diacritice = diacritice;
    }

    public List<Strazi> getStrazi() {
        return strazi;
    }

    public void setStrazi(List<Strazi> strazi) {
        this.strazi = strazi;
    }

    @Override
    public String toString() {
        return "Localitati{" +
                "nume='" + nume + '\'' +
                ", diacritice='" + diacritice + '\'' +
                ", strazi=" + strazi +
                '}';
    }
}
