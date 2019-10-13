package com.plebicom.site.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plebicom.persistence.entity.Article;
import com.plebicom.site.dto.ArticleDTO;

@Service
public class ArticleFactory {
	
	@Autowired
	private BrandFactory brandFactory;
	
	public ArticleDTO createDTOFromEntity(Article article)
	{
		if(article == null) 
		{
			return null;
		}
		
		ArticleDTO dto = new ArticleDTO();
		dto.setDescription(article.getDescription());
		dto.setName(article.getName());
		dto.setBrand(brandFactory.createDTOFromEntity(article.getBrand()));
		return dto;
	}
	
	public Article createEntityFromDTO(ArticleDTO dto)
	{
		if(dto == null) 
		{
			return null;
		}
		
		Article article = new Article();
		article.setDescription(dto.getDescription());
		article.setName(dto.getName());
		return article;
	}
}
