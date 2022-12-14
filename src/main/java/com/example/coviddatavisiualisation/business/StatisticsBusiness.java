package com.example.coviddatavisiualisation.business;

import com.example.coviddatavisiualisation.dao.StatisticsDAO;
import com.example.coviddatavisiualisation.entity.Country;
import com.example.coviddatavisiualisation.exception.StatisticsException;
import com.example.coviddatavisiualisation.helpers.DateFormater;
import com.example.coviddatavisiualisation.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsBusiness implements StatisticsService
{
    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 255;
    private final StatisticsDAO statisticsDAO;
    @Autowired
    public StatisticsBusiness(StatisticsDAO statisticsDAO)
    {
        this.statisticsDAO = statisticsDAO;
    }

    /**
     * this function return a list of details about the country with the name given in parameter
     * @param name the name of the country
     * @return a list of details about the country with the name given in parameter
     */
    @Override
    public List<Country> findByName(String name) {
        if ( name == null || name.isEmpty() || name.isBlank() || name.length() < MIN_LENGTH || name.length() > MAX_LENGTH )
        {
            throw new StatisticsException(
                    "Le nom doit être une chaîne de caractères de minimum "+MIN_LENGTH+" caractères et "+MAX_LENGTH+" maximum",
                    HttpStatus.BAD_REQUEST
            );
        }
        List<Country> countries = this.statisticsDAO.findByName(name);
        if ( countries.isEmpty() )
        {
            StringBuilder message = new StringBuilder();
            message.append("Aucun pays avec le nom '");
            message.append(name);
            message.append("' n'a été trouvé\n");
            message.append("Liste des pays disponibles : \n");
            this.statisticsDAO.getCountries().stream().map(Country::getPays).distinct().forEach(pays -> message.append(pays).append("\n"));
            throw new StatisticsException(message.toString(), HttpStatus.NOT_FOUND);
        }
        return countries;
    }

    /**
     * this function return a list of details about the country with the name given in parameter
     * for the date given in parameter or it returns a DEFAULT_COUNTRY if the country doesn't exist
     * or the date doesn't exist
     * @param name the name of the country
     * @param date the date requested by the user in the format dd-mm-yyyy
     * @return a Details object with the details about the country with the name given in parameter
     */
    @Override
    public Optional<Country> findByNameAndDate(String name, String date) {
        if ( name == null || name.isBlank() || name.length() < MIN_LENGTH || name.length() > MAX_LENGTH )
        {
            throw new StatisticsException(
                    "Le nom doit être une chaîne de caractères de minimum "+MIN_LENGTH+" caractères et "+MAX_LENGTH+" maximum",
                    HttpStatus.BAD_REQUEST
            );
        }
        if ( date == null || date.isBlank() || DateFormater.getDate(date).isAfter(LocalDate.now()) )
        {
            throw new StatisticsException(
                    "La date doit être une chaîne de caractères au format jj-mm-aaaa et ne doit pas être supérieure à la date du jour",
                    HttpStatus.BAD_REQUEST
            );
        }
        Optional<Country> c1 = this.statisticsDAO.findByNameAndDate(name, DateFormater.getDate(date));
        if ( c1.isEmpty() )
        {
            if ( this.statisticsDAO.findByName(name).isEmpty() )
            {
                StringBuilder message = new StringBuilder();
                message.append("Aucun pays avec le nom '");
                message.append(name);
                message.append("' n'a été trouvé\n");
                message.append("Liste des pays disponibles : \n");
                this.statisticsDAO.getCountries().stream().map(Country::getPays).distinct().forEach(pays -> message.append(pays).append("\n"));
                throw new StatisticsException(message.toString(), HttpStatus.NOT_FOUND);
            }
            throw new StatisticsException(
                    "La date "+date+" n'existe pas pour le pays "+name,
                    HttpStatus.NOT_FOUND
            );
        }
        System.out.println(c1.get());
        Optional<Country> c2 = this.statisticsDAO.findByNameAndDate(name, DateFormater.getDate(date).minusDays(1));
        if ( c2.isPresent() )
        {
            Country country = new Country();
            country.setTauxInfecte(c1.get().getTauxInfecte());
            country.setTauxGuerison(c1.get().getTauxGuerison());
            country.setTauxDeces(c1.get().getTauxDeces());
            country.setInfectes(c1.get().getInfectes()-c2.get().getInfectes());
            country.setDeces(c1.get().getDeces());
            country.setPays(c1.get().getPays());
            country.setDate(c1.get().getDate());
            country.setGuerisons(c1.get().getGuerisons());
            country.setInfectes(Math.abs(country.getInfectes() - c2.get().getInfectes()));
            return Optional.of(country);
        }
        return c1;
    }

    /**
     * this function return a list of details about the country with the name given in parameter
     * for today or it returns a DEFAULT_COUNTRY if the country doesn't exist
     * @param name the name of the country
     * @return a Details object with the details about the country with the name given in parameter
     */
    @Override
    public Optional<Country> findByNameToday(String name) {
        if ( name == null || name.isBlank() || name.length() < MIN_LENGTH || name.length() > MAX_LENGTH )
        {
            throw new StatisticsException(
                    "Le nom doit être une chaîne de caractères de minimum "+MIN_LENGTH+" caractères et "+MAX_LENGTH+" maximum",
                    HttpStatus.BAD_REQUEST
            );
        }
        return this.statisticsDAO.findByNameAndDate(name, LocalDate.now());
    }
}
