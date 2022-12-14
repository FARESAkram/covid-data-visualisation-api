package com.example.coviddatavisiualisation.entity;

import java.time.LocalDate;

public class Country
{

    public static final Country DEFAULT = new Country(
        LocalDate.now(),
        "DEFAULT",
        0,
        0,
        0,
        0,
        0,
        0
    );

    private LocalDate date;

    private String pays;

    private long infectes;

    private long deces;

    private long guerisons;

    private double tauxDeces;

    private double tauxGuerison;

    private double tauxInfecte;

    public Country()
    {
    }

    public Country(LocalDate date, String pays, long infectes, long deces, long guerisons, double tauxDeces, double tauxGuerison, double tauxInfecte) {
        this.date = date;
        this.pays = pays;
        this.infectes = infectes;
        this.deces = deces;
        this.guerisons = guerisons;
        this.tauxDeces = tauxDeces;
        this.tauxGuerison = tauxGuerison;
        this.tauxInfecte = tauxInfecte;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public long getInfectes() {
        return infectes;
    }

    public void setInfectes(long infectes) {
        this.infectes = infectes;
    }

    public long getDeces() {
        return deces;
    }

    public void setDeces(long deces) {
        this.deces = deces;
    }

    public long getGuerisons() {
        return guerisons;
    }

    public void setGuerisons(long guerisons) {
        this.guerisons = guerisons;
    }

    public double getTauxDeces() {
        return tauxDeces;
    }

    public void setTauxDeces(double tauxDeces) {
        this.tauxDeces = tauxDeces;
    }

    public double getTauxGuerison() {
        return tauxGuerison;
    }

    public void setTauxGuerison(double tauxGuerison) {
        this.tauxGuerison = tauxGuerison;
    }

    public double getTauxInfecte() {
        return tauxInfecte;
    }

    public void setTauxInfecte(double tauxInfecte) {
        this.tauxInfecte = tauxInfecte;
    }

    @Override
    public String toString()
    {
        return "Country{" +
                "date=" + date +
                ", pays='" + pays + '\'' +
                ", infectes=" + infectes +
                ", deces=" + deces +
                ", guerisons=" + guerisons +
                ", tauxDeces=" + tauxDeces +
                ", tauxGuerison=" + tauxGuerison +
                ", tauxInfecte=" + tauxInfecte +
                '}';
    }
}
