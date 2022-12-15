package com.example.coviddatavisiualisation;

import com.example.coviddatavisiualisation.dao.StatisticsDAOImpl;
import com.example.coviddatavisiualisation.entity.Country;
import com.example.coviddatavisiualisation.helpers.Downloader;
import com.example.coviddatavisiualisation.helpers.Serializer;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CovidDataVisualisationApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(CovidDataVisualisationApplication.class, args);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 6)
    @PostConstruct
    public void init()
    {
        Downloader.download("https://coronavirus.politologue.com/data/coronavirus/coronacsv.aspx?format=csv&t=pays");
        List<Country> countries = Serializer.serialize(new File("src/main/resources/data.csv"));
        StatisticsDAOImpl.setCountries(countries);
    }
}
