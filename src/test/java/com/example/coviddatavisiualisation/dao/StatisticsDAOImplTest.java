package com.example.coviddatavisiualisation.dao;

import com.example.coviddatavisiualisation.dao.StatisticsDAO;
import com.example.coviddatavisiualisation.dao.StatisticsDAOImpl;
import com.example.coviddatavisiualisation.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class StatisticsDAOImplTest
{
    private static Country COUNTRY1 = new Country(
            LocalDate.now(),
            "France",
            100,
            10,
            90,
            0.1,
            0.9,
            0.1
    );
    private static Country COUNTRY2 = new Country(
            LocalDate.of(2022,12,8),
            "Belgique",
            200,
            20,
            180,
            0.2,
            0.8,
            0.2
    );
    private static Country COUNTRY3 = new Country(
            LocalDate.of(2022,12,9),
            "Allemagne",
            300,
            30,
            270,
            0.3,
            0.7,
            0.3
    );

    private static final List<Country> COUNTRIES = List.of(COUNTRY1, COUNTRY2, COUNTRY3);

    private static StatisticsDAO STATISTIC_DAO;

    @BeforeEach
    public void init()
    {
        STATISTIC_DAO = new StatisticsDAOImpl();
        Assert.notNull(STATISTIC_DAO, "StatisticsDAOImpl is null");
        StatisticsDAOImpl.setCountries(COUNTRIES);
        Assert.notNull(STATISTIC_DAO.getCountries(), "Countries is null");
    }
    @Test
    public void testGetAllCountries()
    {
        List<Country> countries = STATISTIC_DAO.getCountries();
        Assert.isTrue(countries.size() == COUNTRIES.size(), "Countries size is not equal");
        Assert.isTrue(countries.containsAll(COUNTRIES), "Countries are not equal");
    }

    @Test
    public void testGetCountryByName()
    {
        List<Country> country = STATISTIC_DAO.findByName("France");
        Assert.isTrue(country.size() == 1, "Country size is not equal");
        Assert.isTrue(country.contains(COUNTRY1), "Country is not equal");

        country = STATISTIC_DAO.findByName("Belgique");
        Assert.isTrue(country.size() == 1, "Country size is not equal");
        Assert.isTrue(country.contains(COUNTRY2), "Country is not equal");

        country = STATISTIC_DAO.findByName("Allemagne");
        Assert.isTrue(country.size() == 1, "Country size is not equal");
        Assert.isTrue(country.contains(COUNTRY3), "Country is not equal");
    }

    @Test
    public void testGetCountryByWrongName()
    {
        List<Country> country = STATISTIC_DAO.findByName("Francee");
        Assert.isTrue(country.size() == 0, "Country size is not equal");
    }

    @Test
    public void testGetCountryByDate()
    {
        Optional<Country> country = STATISTIC_DAO.findByNameAndDate("France", LocalDate.now());
        Assert.isTrue(country.isPresent(), "Country is not present");
        Assert.isTrue(country.get().equals(COUNTRY1), "Country is not equal");

        country = STATISTIC_DAO.findByNameAndDate("Belgique", LocalDate.of(2022,12,8));
        Assert.isTrue(country.isPresent(), "Country is not present");
        Assert.isTrue(country.get().equals(COUNTRY2), "Country is not equal");

        country = STATISTIC_DAO.findByNameAndDate("Allemagne", LocalDate.of(2022,12,9));
        Assert.isTrue(country.isPresent(), "Country is not present");
        Assert.isTrue(country.get().equals(COUNTRY3), "Country is not equal");
    }

    @Test
    public void testGetCountryByWrongDate()
    {
        Optional<Country> country = STATISTIC_DAO.findByNameAndDate("France", LocalDate.of(2022,12,8));
        Assert.isTrue(country.isEmpty(), "Country is present");
    }

    @Test
    public void testGetCountryByWrongNameAndDate()
    {
        Optional<Country> country = STATISTIC_DAO.findByNameAndDate("Francee", LocalDate.of(2022,12,8));
        Assert.isTrue(country.isEmpty(), "Country is present");
    }
    @Test
    public void testGetCountryByNameToday()
    {
        Optional<Country> country = STATISTIC_DAO.findByNameAndDate("France", LocalDate.now());
        Assert.isTrue(country.isPresent(), "Country is not present");
    }


}
