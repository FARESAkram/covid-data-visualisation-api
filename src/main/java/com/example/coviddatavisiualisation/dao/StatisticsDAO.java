package com.example.coviddatavisiualisation.dao;

import com.example.coviddatavisiualisation.entity.Country;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StatisticsDAO
{
    /**
     * this function is used to find the details of a country by its name
     * @param name the name of the country
     * @return a list of countries with the same name
     */
    List<Country> findByName(String name);

    /**
     * this function is used to find the details of a country by its name and date
     * @param name the name of the country
     * @param date the date requested
     * @return an optional of the country
     */
    Optional<Country> findByNameAndDate(String name, LocalDate date);

    /**
     * this function is used to find the details of all countries
     * @return a list of countries
     */
    List<Country> getCountries();
}
