package com.example.coviddatavisiualisation.entity;

import com.example.coviddatavisiualisation.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDate;


public class CountryTest
{
    private static final LocalDate date = LocalDate.now();

    private static final  String pays = "France";

    private static final  long infectes = 100;

    private static final  long deces = 10;

    private static final  long guerisons = 90;

    private static final  double tauxDeces = 0.1;

    private static final  double tauxGuerison = 0.9;

    private static final  double tauxInfecte = 0.1;


    @Test
    public void contextLoads(){}

    @Test
    public void testCountryGetters()
    {
        Country country = new Country(date, pays, infectes, deces, guerisons, tauxDeces, tauxGuerison, tauxInfecte);

        Assert.isTrue(country.getDate().equals(date), "Date is not equal");
        Assert.isTrue(country.getPays().equals(pays), "Pays is not equal");
        Assert.isTrue(country.getInfectes() == infectes, "Infectes is not equal");
        Assert.isTrue(country.getDeces() == deces, "Deces is not equal");
        Assert.isTrue(country.getGuerisons() == guerisons, "Guerisons is not equal");
        Assert.isTrue(country.getTauxDeces() == tauxDeces, "TauxDeces is not equal");
        Assert.isTrue(country.getTauxGuerison() == tauxGuerison, "TauxGuerison is not equal");
        Assert.isTrue(country.getTauxInfecte() == tauxInfecte, "TauxInfecte is not equal");

    }

    @Test
    public void testCountrySetters()
    {
        Country country = new Country();

        country.setDate(date);
        country.setPays(pays);
        country.setInfectes(infectes);
        country.setDeces(deces);
        country.setGuerisons(guerisons);
        country.setTauxDeces(tauxDeces);
        country.setTauxGuerison(tauxGuerison);
        country.setTauxInfecte(tauxInfecte);

        Assert.isTrue(country.getDate().equals(date), "Date is not equal");
        Assert.isTrue(country.getPays().equals(pays), "Pays is not equal");
        Assert.isTrue(country.getInfectes() == infectes, "Infectes is not equal");
        Assert.isTrue(country.getDeces() == deces, "Deces is not equal");
        Assert.isTrue(country.getGuerisons() == guerisons, "Guerisons is not equal");
        Assert.isTrue(country.getTauxDeces() == tauxDeces, "TauxDeces is not equal");
        Assert.isTrue(country.getTauxGuerison() == tauxGuerison, "TauxGuerison is not equal");
        Assert.isTrue(country.getTauxInfecte() == tauxInfecte, "TauxInfecte is not equal");

    }

    @Test
    public void testToString()
    {
        Country country = new Country(date, pays, infectes, deces, guerisons, tauxDeces, tauxGuerison, tauxInfecte);

        String expected = "Country{" +
                "date=" + date +
                ", pays='" + pays + '\'' +
                ", infectes=" + infectes +
                ", deces=" + deces +
                ", guerisons=" + guerisons +
                ", tauxDeces=" + tauxDeces +
                ", tauxGuerison=" + tauxGuerison +
                ", tauxInfecte=" + tauxInfecte +
                '}';

        Assert.isTrue(country.toString().equals(expected), "toString is not equal");
    }
}
