package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.OpenFoodApiArticle;
import org.springframework.data.repository.CrudRepository;

public interface OpenFoodApiArticleRepository extends CrudRepository<OpenFoodApiArticle, Integer> {

	OpenFoodApiArticle findByEanCode(String eanCode);

}
