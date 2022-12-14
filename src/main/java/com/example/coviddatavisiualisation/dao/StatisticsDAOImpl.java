package com.example.coviddatavisiualisation.dao;

import com.example.coviddatavisiualisation.entity.Country;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StatisticsDAOImpl implements StatisticsDAO
{

    private static List<Country> countries;

    public static void setCountries(List<Country> countries)
    {
        StatisticsDAOImpl.countries = countries;
    }

    /**
     * this function is used to find the details of a country by its name
     * @param name the name of the country
     * @return a list of countries with the same name
     */
    @Override
    public List<Country> findByName(String name)
    {
        return countries.stream().filter(country -> country.getPays().equals(name)).collect(Collectors.toList());
    }

    /**
     * this function is used to find the details of a country by its name and date
     * @param name the name of the country
     * @param date the date requested
     * @return an optional of the country
     */
    @Override
    public Optional<Country> findByNameAndDate(String name, LocalDate date)
    {
        AtomicInteger count = new AtomicInteger();
        Stream<Country> stream = countries
                .stream()
                .filter(country -> country.getPays().equals(name) && country.getDate().isEqual(date))
                .peek(country -> count.getAndIncrement());
        return stream.findFirst();
    }

    /**
     * this function is used to find the details of all countries
     * @return a list of countries
     */
    public List<Country> getCountries()
    {
        return countries;
    }

}
