package com.example.coviddatavisiualisation.service;

import com.example.coviddatavisiualisation.entity.Country;

import java.util.List;
import java.util.Optional;

public interface StatisticsService
{
    /**
     * this function return a list of details about the country with the name given in parameter
     * @param name the name of the country
     * @return a list of details about the country with the name given in parameter
     */
    List<Country> findByName(String name);

    /**
     * this function return a list of details about the country with the name given in parameter
     * for the date given in parameter or it returns a DEFAULT_COUNTRY if the country doesn't exist
     * or the date doesn't exist
     * @param name the name of the country
     * @param date the date requested by the user in the format dd-mm-yyyy
     * @return a Details object with the details about the country with the name given in parameter
     */
    Optional<Country> findByNameAndDate(String name, String date);

    /**
     * this function return a list of details about the country with the name given in parameter
     * for today or it returns a DEFAULT_COUNTRY if the country doesn't exist
     * @param name the name of the country
     * @return a Details object with the details about the country with the name given in parameter
     */
    Optional<Country> findByNameToday(String name);
}
