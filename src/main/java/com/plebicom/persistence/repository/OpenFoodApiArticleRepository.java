package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.OpenFoodApiArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenFoodApiArticleRepository extends CrudRepository<OpenFoodApiArticle, Integer> {

	OpenFoodApiArticle findByEanCode(String eanCode);

}
