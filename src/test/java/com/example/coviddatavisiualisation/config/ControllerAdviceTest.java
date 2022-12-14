package com.example.coviddatavisiualisation.config;

import com.example.coviddatavisiualisation.exception.StatisticsException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.Map;

public class ControllerAdviceTest
{
    @Test
    public void testHandleStatisticsException()
    {
        ControllerAdvice controllerAdvice = new ControllerAdvice();
        ResponseEntity<Object> response = controllerAdvice.handleStatisticsException(new StatisticsException("Test"),null);

        Assert.isTrue(response.getStatusCode().value() == 500, "The status code is not the same");

        Assert.notNull(response.getBody(), "The body is null");
        Map<String , String > body = (Map<String, String>) response.getBody();
        Assert.isTrue(body.get("Error").equals("Test"), "The message is not the same");


    }
}
