package com.plebicom.service.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.plebicom.service.dto.OpenFoodApiArticleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenApiDTOFactory {

    public OpenFoodApiArticleDTO getOpenFoodApiArticleFromString(String articleStr){
        final Gson gson = new GsonBuilder().create();
        try{
            return gson.fromJson(articleStr, OpenFoodApiArticleDTO.class);
        }
        catch(JsonSyntaxException e){
            log.debug(String.format("Could not parse the following string %s , reason : %s",articleStr, e.getMessage()));
            return null;
        }
    }
}
