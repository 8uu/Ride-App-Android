package com.example.rideapp.Network;

import java.util.List;

public class Item {
    private String nume;
    private String prescurtare;
    private List<Localitati> localitati;

    public Item(String nume, String prescurtare, List<Localitati> localitati) {
        this.nume = nume;
        this.prescurtare = prescurtare;
        this.localitati = localitati;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrescurtare() {
        return prescurtare;
    }

    public void setPrescurtare(String prescurtare) {
        this.prescurtare = prescurtare;
    }

    public List<Localitati> getLocalitati() {
        return localitati;
    }

    public void setLocalitati(List<Localitati> localitati) {
        this.localitati = localitati;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nume='" + nume + '\'' +
                ", prescurtare='" + prescurtare + '\'' +
                ", localitati=" + localitati +
                '}';
    }
}
