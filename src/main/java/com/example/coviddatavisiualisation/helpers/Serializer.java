package com.example.coviddatavisiualisation.helpers;

import com.example.coviddatavisiualisation.entity.Country;
import com.example.coviddatavisiualisation.exception.StatisticsException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Serializer
{
    public static List<Country> serialize(File file)
    {
        try(FileInputStream fis = new FileInputStream(file))
        {
            List<Country> countries = Files.lines(file.toPath())
                    .skip(9)
                    .map(line -> line.split(";"))
                    .map(Serializer::mapToCountry)
                    .collect(Collectors.toList());
            return countries;
        }
        catch (IOException e)
        {
            throw new StatisticsException("Error while reading file",e);
        }
    }

    public static Country mapToCountry(String[] str)
    {
        LocalDate date = LocalDate.parse(str[0]);
        String pays = str[1];
        long infectes = Long.parseLong(str[2]);
        long deces = Long.parseLong(str[3]);
        long guerisons = Long.parseLong(str[4]);
        double tauxDeces = Double.parseDouble(str[5]);
        double tauxGuerison = Double.parseDouble(str[6]);
        double tauxInfecte = Double.parseDouble(str[7]);
        return new Country(date, pays, infectes, deces, guerisons, tauxDeces, tauxGuerison, tauxInfecte);
    }
}
