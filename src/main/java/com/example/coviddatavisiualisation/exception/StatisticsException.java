package com.example.coviddatavisiualisation.exception;

import org.springframework.http.HttpStatusCode;

public class StatisticsException extends RuntimeException
{

    private HttpStatusCode status;
    public StatisticsException(String message)
    {
        this(message, HttpStatusCode.valueOf(500));
    }

    public StatisticsException(String message, Throwable cause)
    {
        this(message, HttpStatusCode.valueOf(500), cause);
    }

    public StatisticsException(String message, HttpStatusCode status)
    {
        super(message);
        this.status = status;
    }

    public StatisticsException(String message, HttpStatusCode status, Throwable cause)
    {
        super(message, cause);
        this.status = status;
    }

    public HttpStatusCode getStatus()
    {
        return status;
    }

}
