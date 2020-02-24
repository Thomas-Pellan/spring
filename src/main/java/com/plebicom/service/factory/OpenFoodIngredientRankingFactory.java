package com.plebicom.service.factory;

import com.plebicom.persistence.entity.OpenFoodArticle;
import com.plebicom.persistence.entity.OpenFoodIngredient;
import com.plebicom.persistence.entity.OpenFoodIngredientRanking;
import com.plebicom.persistence.repository.OpenFoodIngredientRankingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenFoodIngredientRankingFactory {

    @Autowired
    private OpenFoodIngredientRankingRepository openFoodIngredientRankingRepository;

    public OpenFoodIngredientRanking createOrMergeOpenApiIngredientRanking(Integer rank, OpenFoodArticle article, OpenFoodIngredient ingredient){

        if(article == null ||ingredient == null){
            return null;
        }

        OpenFoodIngredientRanking ranking = new OpenFoodIngredientRanking();
        ranking.setArticle(article);
        ranking.setIngredient(ingredient);
        ranking.setRank(rank);

        return openFoodIngredientRankingRepository.save(ranking);
    }
}
