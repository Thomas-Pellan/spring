package com.plebicom.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.plebicom.persistence.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

	Article findByName(String name);

}
