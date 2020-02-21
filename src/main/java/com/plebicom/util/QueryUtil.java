package com.plebicom.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plebicom.site.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.zip.GZIPInputStream;

@Slf4j
@Service
public class QueryUtil {

    public String getDataAsString(String url){

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.debug(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }

        if(response == null || response.statusCode() != 200){
            log.debug(String.format("Got empty response or error response for the request on url %s", url));
            throw new BusinessException("getData : error during query");
        }

        return response.body();
    }

    public InputStream getFileData(String url, String fileName){

        //Query and get the file from the API
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<InputStream> response;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException | InterruptedException e) {
            log.debug(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }

        if(response == null || response.statusCode() != 200){
            log.debug(String.format("Got empty response or error response for the request on url %s", url));
            throw new BusinessException("getFileData : error during query");
        }


        return response.body();
    }
}
