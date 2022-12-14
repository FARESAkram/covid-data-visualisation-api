package com.example.coviddatavisiualisation.exception;

import com.example.coviddatavisiualisation.exception.StatisticsException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.Assert;

public class StatisticsExceptionTest
{
    @Test
    public void testGettingMessage()
    {
        StatisticsException exception = new StatisticsException("Test");
        Assert.isTrue(exception.getMessage().equals("Test"), "The message is not the same");
    }

    @Test
    public void testGettingStatus()
    {
        StatisticsException exception = new StatisticsException("Test");
        Assert.isTrue(exception.getStatus().equals(HttpStatusCode.valueOf(500)), "The status is not the same");
    }

    @Test
    public void testGettingMessageAndStatus()
    {
        StatisticsException exception = new StatisticsException("Test", HttpStatusCode.valueOf(200));
        Assert.isTrue(exception.getMessage().equals("Test"), "The message is not the same");
        Assert.isTrue(exception.getStatus().equals(HttpStatusCode.valueOf(200)), "The status is not the same");
    }

    @Test
    public void testGettingMessageAndStatusAndCause()
    {
        StatisticsException exception = new StatisticsException("Test", HttpStatusCode.valueOf(200), new Exception());
        Assert.isTrue(exception.getMessage().equals("Test"), "The message is not the same");
        Assert.isTrue(exception.getStatus().equals(HttpStatusCode.valueOf(200)), "The status is not the same");
        Assert.isTrue(exception.getCause() instanceof Exception, "The cause is not the same");
    }

    @Test
    public void testGettingMessageAndCause()
    {
        StatisticsException exception = new StatisticsException("Test", new Exception());
        Assert.isTrue(exception.getMessage().equals("Test"), "The message is not the same");
        Assert.isTrue(exception.getStatus().equals(HttpStatusCode.valueOf(500)), "The status is not the same");
        Assert.isTrue(exception.getCause() instanceof Exception, "The cause is not the same");
    }
}
