package com.example.coviddatavisiualisation.repository;

import com.example.coviddatavisiualisation.entity.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

public class StatisticsControllerTest
{

    private static final String BASE_URL = "http://localhost:8080/api";
    private static final String NAME_URL = BASE_URL + "/oneCountryData?country=";

    private static final String NAME_AND_DATE_URL = BASE_URL + "/oneCountryDataWithDate?country=";


    @Test
    public void testFindByName()
    {
        HttpGet request = new HttpGet(NAME_URL + "France");
        try(CloseableHttpClient client = HttpClientBuilder.create().build())
        {
            HttpResponse response = client.execute(request);
            Assert.isTrue(HttpStatus.SC_OK == response.getStatusLine().getStatusCode(), "Status code is not 200");
            // assert response body is not empty and contains the expected images
            Assert.notNull(response.getEntity(), "Response entity is null");
            Assert.notNull(response.getEntity().getContent(), "Response entity content is null");
            byte[] b = response.getEntity().getContent().readAllBytes();
            Assert.notNull(b, "Response entity content is null");
            Assert.isTrue(b.length > 0, "Response entity content is empty");
            List<Country> countries = new ObjectMapper().readValue(b, List.class);
            Assert.notNull(countries, "Countries is null");
            Assert.isTrue(countries.size() > 0, "Countries is empty");
        }
        catch (Exception e)
        {
            // fail the build
            Assert.isTrue(false, "Exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetByNameWrong()
    {
        HttpGet request = new HttpGet(NAME_URL + "Franceee");
        try(CloseableHttpClient client = HttpClientBuilder.create().build())
        {
            HttpResponse response = client.execute(request);
            Assert.isTrue(HttpStatus.SC_BAD_REQUEST == response.getStatusLine().getStatusCode(), "Status code is not 400");
            // assert response body is not empty and contains the expected images
            Assert.notNull(response.getEntity(), "Response entity is null");
            Assert.notNull(response.getEntity().getContent(), "Response entity content is null");
            byte[] b = response.getEntity().getContent().readAllBytes();
            Assert.notNull(b, "Response entity content is null");
            Assert.isTrue(b.length > 0, "Response entity content is empty");
            Map<String, String> map = new ObjectMapper().readValue(b, Map.class);
            Assert.notNull(map, "Map is null");
            Assert.isTrue(map.size() > 0, "Map is empty");
            Assert.isTrue(map.containsKey("Error"), "Map does not contain key 'error'");
        }
        catch (Exception e)
        {
            // fail the build
            Assert.isTrue(false, "Exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetByDate()
    {
        HttpGet request = new HttpGet(NAME_AND_DATE_URL + "France&date=12-12-2022");
        try(CloseableHttpClient client = HttpClientBuilder.create().build())
        {
            HttpResponse response = client.execute(request);
            Assert.isTrue(HttpStatus.SC_OK == response.getStatusLine().getStatusCode(), "Status code is not 200");
            // assert response body is not empty and contains the expected images
            Assert.notNull(response.getEntity(), "Response entity is null");
            Assert.notNull(response.getEntity().getContent(), "Response entity content is null");
            byte[] b = response.getEntity().getContent().readAllBytes();
            Assert.notNull(b, "Response entity content is null");
            Assert.isTrue(b.length > 0, "Response entity content is empty");

            Country country = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(b, Country.class);
            Assert.notNull(country, "Country is null");
            Assert.isTrue(country.getPays().equals("France"), "Country is not France");
        }
        catch (Exception e)
        {
            // fail the build
            Assert.isTrue(false, "Exception: " + e.getMessage());
        }
    }
}
