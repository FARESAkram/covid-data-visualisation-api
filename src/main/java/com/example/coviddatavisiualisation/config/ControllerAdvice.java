package com.example.coviddatavisiualisation.config;

import com.example.coviddatavisiualisation.exception.StatisticsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler
{
    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     * @param ex MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @param request WebRequest
     * @return the ErrorInfo object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request)
    {
        Error error = new Error(ex.getMessage(), status);
        return new ResponseEntity<>(error,headers,status);
    }

    /**
     * Handle StatisticsException. Created to encapsulate errors with more details.
     * @param ex the StatisticsException that is thrown
     * @param request the WebRequest
     * @return the ErrorInfo object
     */
    @ExceptionHandler({StatisticsException.class})
    public ResponseEntity<Object> handleStatisticsException(StatisticsException ex, WebRequest request)
    {
        Error error = new Error(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(error,new HttpHeaders(),ex.getStatus());
    }

    /**
     * this inner class is used to encapsulate the error message and the status code
     */
    static class Error extends HashMap<String,String>
    {
        Error(String message, HttpStatusCode status)
        {
            this.put("Error", message);
            this.put("Status", status.toString());
        }
    }
}
