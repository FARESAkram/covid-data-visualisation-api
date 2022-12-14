package com.example.coviddatavisiualisation.repository;

import com.example.coviddatavisiualisation.entity.Country;
import com.example.coviddatavisiualisation.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class StatisticsController
{
    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService)
    {
        this.statisticsService = statisticsService;
    }

    /**
     * this function is used to find the details of a country by its name
     * @param countryName the name of the country
     * @return a list of countries with the same name
     */
    @GetMapping(value = "/oneCountryData" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Country>> getCountryDetails(@RequestParam(name = "country") String countryName)
    {
        List<Country> data = this.statisticsService.findByName(countryName);
        return ResponseEntity.ok(data);
    }

    /**
     * this function is used to find the details of a country by its name and date
     * @param countryName the name of the country
     * @param date the date requested
     * @return an optional of the country
     */
    @GetMapping(value = "/oneCountryDataWithDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> getCountryDetailsForADate(
            @RequestParam(name = "country") String countryName,
            @RequestParam(name = "date") String date)
    {
        Country data = this.statisticsService.findByNameAndDate(countryName, date).orElse(Country.DEFAULT);
        return ResponseEntity.ok(data);
    }

    /**
     * this function is used to find the details of a country by its name and today's date
     * @param countryName the name of the country
     * @return an optional of the country
     */
    @GetMapping(value = "/todayCountryData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> getCountryDetailsToday(
            @RequestParam(name = "country") String countryName)
    {

        Country country = this.statisticsService.findByNameToday(countryName).orElse(Country.DEFAULT);
        return ResponseEntity.ok(country);
    }
}
