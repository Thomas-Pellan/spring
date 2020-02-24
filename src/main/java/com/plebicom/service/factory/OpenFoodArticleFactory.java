package com.plebicom.service.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.plebicom.persistence.entity.OpenFoodArticle;
import com.plebicom.persistence.entity.OpenFoodIngredient;
import com.plebicom.persistence.repository.OpenFoodArticleRepository;
import com.plebicom.service.dto.OpenFoodApiArticleDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OpenFoodArticleFactory {

    private static final int SAVE_DATA_MODULO = 1000;

    @Autowired
    private OpenFoodArticleRepository openFoodArticleRepository;

    @Autowired
    private OpenFoodIngredientFactory openFoodIngredientFactory;

    @Autowired
    private OpenFoodIngredientRankingFactory openFoodIngredientRankingFactory;

    public List<OpenFoodArticle> createOrMergeOpenApiArticleFromDto(List<OpenFoodApiArticleDTO> dtos){
        if(CollectionUtils.isEmpty(dtos)){
            return null;
        }

        //Split save by groups to avoid too much persistence calls on huge data volumes
        List<OpenFoodArticle> articles = new ArrayList<>();

        dtos.removeIf(dto -> dto == null);

        List<OpenFoodApiArticleDTO> tmpDtos = new ArrayList<>();
        for(int i = 0; i < dtos.size(); i++){

            tmpDtos.add(dtos.get(i));
            if(i%SAVE_DATA_MODULO == 0 || (dtos.size()-i) < SAVE_DATA_MODULO){
                articles.addAll(tmpDtos
                        .stream()
                        .map(dto -> createOrMergeOpenApiArticleFromDto(dto))
                        .collect(Collectors.toList())
                );
                tmpDtos = new ArrayList<>();
            }
        }

        return articles;
    }

    public OpenFoodArticle createOrMergeOpenApiArticleFromDto(OpenFoodApiArticleDTO dto){

        if(dto == null){
            return null;
        }

        OpenFoodArticle article = new OpenFoodArticle();
        OpenFoodArticle existing = openFoodArticleRepository.findByEanCode(dto.getId());
        if(dto.getId() != null && existing != null){
            article = existing;
        }
        else
        {
            article.setEanCode(dto.getId());
        }

        if(StringUtils.isNotBlank(dto.getLastModified())){
            Instant instant = Instant.ofEpochMilli(Long.parseLong(dto.getLastModified()));
            article.setLastModified(instant.atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        if(StringUtils.isNotBlank(dto.getCreated())){
            Instant instant = Instant.ofEpochMilli(Long.parseLong(dto.getCreated()));
            article.setCreated(instant.atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        article.setProductName(dto.getProductName());
        article.setNutritionGrade(dto.getNutritionGrade());

        //Persist to get id for ingredient list
        final OpenFoodArticle persistedArticle = openFoodArticleRepository.save(article);

        //Create ingredients and their ranking in the article
        if(!CollectionUtils.isEmpty(dto.getIngredients())){
            dto.getIngredients()
                .stream()
                .forEach(ingr -> {
                    OpenFoodIngredient ingredient = openFoodIngredientFactory.createOrMergeOpenApiIngredientFromDto(ingr);
                    openFoodIngredientRankingFactory.createOrMergeOpenApiIngredientRanking(ingr.getRank(), persistedArticle, ingredient);
                });
        }

        return openFoodArticleRepository.save(persistedArticle);
    }

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
