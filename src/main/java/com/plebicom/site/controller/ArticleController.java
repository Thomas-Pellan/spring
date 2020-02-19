package com.plebicom.site.controller;

import com.plebicom.site.dto.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plebicom.service.ArticleService;
import com.plebicom.site.dto.ArticleDTO;

@Controller
@RequestMapping(path="/articles")
@CrossOrigin
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	@PostMapping(path="/create")
	public @ResponseBody ResponseEntity createArticle (@RequestBody ArticleDTO article) {
		return ResponseEntity.ok(articleService.createArticle(article));
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody ResponseEntity deleteArticle (@RequestBody ArticleDTO article) {
		articleService.removeArticle(article);
		return ResponseEntity.ok(null);
	}

	@GetMapping(path="/all")
	public @ResponseBody ResponseEntity getAllArticles() {
		return ResponseEntity.ok(new ApiResponseDTO<>(articleService.getAllArticles()));
	}
	
	@GetMapping(path="/name")
	public @ResponseBody ResponseEntity getArticleByName(@RequestParam String name) {
		return ResponseEntity.ok(articleService.getArticleByName(name));
	}
}
