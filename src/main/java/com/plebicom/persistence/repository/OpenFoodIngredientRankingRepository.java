package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.OpenFoodArticle;
import com.plebicom.persistence.entity.OpenFoodIngredientRanking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenFoodIngredientRankingRepository extends CrudRepository<OpenFoodIngredientRanking, Integer> {

    List<OpenFoodIngredientRanking> findByArticle(OpenFoodArticle article);
}
