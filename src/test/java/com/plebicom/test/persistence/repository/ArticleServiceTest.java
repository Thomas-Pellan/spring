package com.plebicom.test.persistence.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.plebicom.persistence.entity.Article;
import com.plebicom.persistence.entity.Brand;
import com.plebicom.persistence.repository.ArticleRepository;
import com.plebicom.persistence.repository.BrandRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ArticleServiceTest {
	
	@Autowired
    private ArticleRepository articleRepository;
	
	@Autowired
    private BrandRepository brandRepository;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Before
    public void initTest() throws Exception {
		
		if(brandRepository.findByName("initTestBrand") != null)
		{
			return;
		}
		
		Brand brand1 = new Brand();
		brand1.setName("initTestBrand");
		Brand persisted = brandRepository.save(brand1);
		if(articleRepository.findByName("initTest") != null)
		{
			return;
		}
		
		Article article1 = new Article();
		article1.setName("initTest");
		article1.setBrand(persisted);
		articleRepository.save(article1);
		
		// Making sure the deleted article does not exists
		Article articleToDelete = articleRepository.findByName("article for delete");
		if(articleToDelete != null)
		{
			articleRepository.delete(articleToDelete);
		}
    }
	
	 @Test
	 public void testGetAll() throws Exception {
		 
		 mockMvc.perform(get("/articles/all"))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.type", is("SUCCESS")))
         .andExpect(jsonPath("$.data").isArray())
         .andExpect(jsonPath("$.message").isEmpty())
         .andExpect(jsonPath("$.data").isNotEmpty());
	 }
	 
	 @Test
	 public void testGetByName() throws Exception {
		 
		 mockMvc.perform(get("/articles/name?name=initTest"))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.type", is("SUCCESS")))
         .andExpect(jsonPath("$.message").isEmpty())
         .andExpect(jsonPath("$.data").isNotEmpty());
		 
		 mockMvc.perform(get("/articles/name?name=thisshouldnotexist"))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.type", is("SUCCESS")))
         .andExpect(jsonPath("$.message").isEmpty())
         .andExpect(jsonPath("$.data").isEmpty());
	 }
	 
	 @Test
	 public void testCreateArticle() throws Exception {
		 
		 String testJson = "{\"name\" : \"new value that does not exists\",\"content\" : \"tdgssg\",\"brand\" : {\"name\" : \"initTestBrand\"}}";
		 
		 mockMvc.perform(post("/articles/create").content(testJson).contentType(MediaType.APPLICATION_JSON_UTF8))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.type", is("SUCCESS")))
         .andExpect(jsonPath("$.message").isEmpty())
         .andExpect(jsonPath("$.data").isNotEmpty());
		 
		 //Trying to create again with same name => error
		 mockMvc.perform(post("/articles/create").content(testJson).contentType(MediaType.APPLICATION_JSON_UTF8))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.type", is("ERROR")))
         .andExpect(jsonPath("$.message").isNotEmpty())
         .andExpect(jsonPath("$.data").isEmpty());
	 }
	 
	 @Test
	 public void testDeleteArticle() throws Exception {
		 
		 String testJson = "{\"name\" : \"article for delete\",\"content\" : \"tdgssg\",\"brand\" : {\"name\" : \"initTestBrand\"}}";
		 
		 mockMvc.perform(post("/articles/create").content(testJson).contentType(MediaType.APPLICATION_JSON_UTF8))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.type", is("SUCCESS")))
         .andExpect(jsonPath("$.message").isEmpty())
         .andExpect(jsonPath("$.data").isNotEmpty());
		 
		 // Deleting article
		 mockMvc.perform(post("/articles/delete").content(testJson).contentType(MediaType.APPLICATION_JSON_UTF8))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.type", is("SUCCESS")))
         .andExpect(jsonPath("$.message").isEmpty())
         .andExpect(jsonPath("$.data").isEmpty());
		 
		 // Article should not exist
		 mockMvc.perform(get("/articles/name?name=article for delete"))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.type", is("SUCCESS")))
         .andExpect(jsonPath("$.message").isEmpty())
         .andExpect(jsonPath("$.data").isEmpty());
	 }
	 
	 @After 
	 public void cleanUp() {
		 
		 Article article = articleRepository.findByName("initTest");
		 if(article != null)
		 {
			 articleRepository.delete(article);
		 }
		 
		 Article article2 = articleRepository.findByName("new value that does not exists");
		 if(article2 != null)
		 {
			 articleRepository.delete(article2);
		 }
		 
		 Brand brand1 = brandRepository.findByName("initTestBrand");
		 if(brand1 != null)
		 {
			 brandRepository.delete(brand1);
		 }
	 }
}
