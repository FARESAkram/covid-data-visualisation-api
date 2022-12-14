package com.example.coviddatavisiualisation.business;

import com.example.coviddatavisiualisation.business.StatisticsBusiness;
import com.example.coviddatavisiualisation.dao.StatisticsDAO;
import com.example.coviddatavisiualisation.dao.StatisticsDAOImpl;
import com.example.coviddatavisiualisation.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class StatisticsBusinessTest
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

    private static StatisticsBusiness STATISTIC_BUSINESS;

    String listCountries ;

    @BeforeEach
    public void init()
    {
        StatisticsDAO STATISTIC_DAO;
        STATISTIC_DAO = new StatisticsDAOImpl();
        Assert.notNull(STATISTIC_DAO, "StatisticsDAOImpl is null");
        StatisticsDAOImpl.setCountries(COUNTRIES);
        Assert.notNull(STATISTIC_DAO.getCountries(), "Countries is null");
        STATISTIC_BUSINESS = new StatisticsBusiness(STATISTIC_DAO);
        Assert.notNull(STATISTIC_BUSINESS, "StatisticsBusiness is null");
        StringBuilder sb = new StringBuilder();
        STATISTIC_DAO.getCountries().stream().map(Country::getPays).distinct().forEach(pays -> sb.append(pays).append("\n"));
        listCountries = sb.toString();
    }


    @Test
    public void testGetByNameValid()
    {
        List<Country> country = STATISTIC_BUSINESS.findByName("France");
        Assert.notNull(country, "Country is null");
        Assert.isTrue(country.size() == 1, "Country size is not 1");
        Assert.isTrue(country.get(0).getPays().equals("France"), "Country name is not France");
    }

    @Test
    public void testGetByNameInvalid()
    {
        try
        {
            List<Country> country = STATISTIC_BUSINESS.findByName("Francee");
            Assert.isTrue(country.size() == 0, "Country size is not 0");
        }
        catch (Exception e)
        {
            String message = "Aucun pays avec le nom '" +
                    "Francee" +
                    "' n'a été trouvé\n" +
                    "Liste des pays disponibles : \n" +
                    listCountries;
            Assert.isTrue(e.getMessage().equals(message), "Exception message is not Country not found");
        }
    }

    @Test
    public void testGetByDateValid()
    {
        try
        {
            Optional<Country> country = STATISTIC_BUSINESS.findByNameAndDate("France", String.valueOf(LocalDate.now()));
        }
        catch (Exception e)
        {
            Assert.isTrue(e.getMessage().equals("La date doit être une chaîne de caractères au format jj-mm-aaaa et ne doit pas être supérieure à la date du jour"), e.getMessage());
        }
    }

    @Test
    public void testGetByDateInvalid()
    {
        try
        {
            Optional<Country> country = STATISTIC_BUSINESS.findByNameAndDate("France", String.valueOf(LocalDate.of(2022,12,10)));
            Assert.isTrue(country.isEmpty(), "Country is present");
        }
        catch (Exception e)
        {
            Assert.isTrue(e.getMessage().equals("La date doit être une chaîne de caractères au format jj-mm-aaaa et ne doit pas être supérieure à la date du jour"), "Exception message is not Country not found");
        }
    }

}
