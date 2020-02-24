package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.OpenFoodArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenFoodArticleRepository extends CrudRepository<OpenFoodArticle, Integer> {

	OpenFoodArticle findByEanCode(String eanCode);

}
