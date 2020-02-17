package com.plebicom.service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.plebicom.site.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plebicom.persistence.entity.Article;
import com.plebicom.persistence.entity.Brand;
import com.plebicom.persistence.repository.ArticleRepository;
import com.plebicom.persistence.repository.BrandRepository;
import com.plebicom.site.dto.ArticleDTO;
import com.plebicom.site.factory.ArticleFactory;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private ArticleFactory articleFactory;
	
	public Iterable<ArticleDTO> getAllArticles() {
			
		Iterable<Article> articles = articleRepository.findAll();
		
		return StreamSupport.stream(articles.spliterator(), false).map(article -> articleFactory.createDTOFromEntity(article))
			    .collect(Collectors.toList());
	}

	public ArticleDTO getArticleByName(String name) {
		
		Article article = articleRepository.findByName(name);
		
		if(article == null)
		{
			return null;
		}
		
		return articleFactory.createDTOFromEntity(article);
	}
	
	public ArticleDTO createArticle(ArticleDTO dto) {
		
		String checkData = checkArticleData(dto);
		if(checkData != null)
		{
			throw new BusinessException(checkData);
		}
		
		Article existing = articleRepository.findByName(dto.getName());
		
		if(existing != null)
		{
			throw new BusinessException( String.format("Article already exists with the name %s", dto.getName()));
		}
		
		Article article = articleFactory.createEntityFromDTO(dto);
		Brand brand = brandRepository.findByName(dto.getBrand().getName());
		article.setBrand(brand);
		
		Article newArticle = articleRepository.save(article);
		
		return articleFactory.createDTOFromEntity(newArticle);
	}
	
	public void removeArticle(ArticleDTO dto) {
		
		if(dto == null)
		{
			throw new BusinessException("No data");
		}
		
		Article existing = articleRepository.findByName(dto.getName());
		
		if(existing == null)
		{
			throw new BusinessException(String.format("Article does not exists with the name %s", dto.getName()));
		}
		
		articleRepository.delete(existing);
	}
	
	private String checkArticleData(ArticleDTO dto) 
	{
	
		if(dto == null) {
			return "No data";
		}
		
		if(StringUtils.isBlank(dto.getName())) {
			return "Article name is mandatory";
		}
		
		if(dto.getBrand() == null) {
			return "Brand is mandatory";
		}
		
		Brand brand = brandRepository.findByName(dto.getBrand().getName());
		
		if(brand == null)
		{
			return "This brand does not exists";
		}
		
		return null;
	}
}
