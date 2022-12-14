package com.example.coviddatavisiualisation.helpers;

import com.example.coviddatavisiualisation.exception.StatisticsException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Downloader
{
    /**
     * this function download the file from the url and save it in the file
     * @param url the url of the file
     */
    public static void download(String url)
    {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        request.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        request.setHeader("Pragma", "no-cache");
        request.setHeader("Expires", "0");

        try
        {
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() != 200)
            {
                throw new StatisticsException("Error while downloading data");
            }
            byte[] bytes = response.getEntity().getContent().readAllBytes();
            File file = new File("src/main/resources/data.csv");
            try(FileOutputStream fos = new FileOutputStream(file))
            {
                fos.write(bytes);
            }
            catch (Throwable e)
            {
                throw new StatisticsException("Error while saving data",e);
            }
        }
        catch (IOException e)
        {
            throw new StatisticsException("Error while downloading data");
        }
    }
}
