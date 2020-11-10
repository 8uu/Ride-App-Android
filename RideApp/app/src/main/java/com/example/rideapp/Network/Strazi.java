package com.example.rideapp.Network;

public class Strazi {
    private String nume;
    private int numar;
    private int cod;

    public Strazi(String nume, int numar, int cod) {
        this.nume = nume;
        this.numar = numar;
        this.cod = cod;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "Strazi{" +
                "nume='" + nume + '\'' +
                ", numar=" + numar +
                ", cod=" + cod +
                '}';
    }
}
