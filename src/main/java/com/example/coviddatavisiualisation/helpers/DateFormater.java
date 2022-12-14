package com.example.coviddatavisiualisation.helpers;

import com.example.coviddatavisiualisation.exception.StatisticsException;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
public class DateFormater
{
    /**
     * this function is used to format a date from a string to a LocalDate
     * in the format dd-MM-yyyy
     * @param date the date to format
     * @return the formatted date
     */
    public static LocalDate getDate(String date)
    {
        try
        {
            String[] dateSplit = date.split("-");
            String dateReversed = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
            return LocalDate.parse(dateReversed);
        }
        catch (Exception e)
        {
            throw new StatisticsException("La date doit être une chaîne de caractères au format jj-mm-aaaa et ne doit pas être supérieure à la date du jour",e);
        }
    }
}
