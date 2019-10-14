package com.plebicom.test.persistence.repository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.plebicom.persistence.entity.Brand;
import com.plebicom.persistence.repository.BrandRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BrandServiceTest {
	
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
		brandRepository.save(brand1);
		
		// Making sure the brand to delete does not exists
		Brand brandToDelete = brandRepository.findByName("testBrandToDelete");
		if(brandToDelete != null)
		{
			brandRepository.delete(brandToDelete);
		}
	}
	
	@Test
	 public void testGetAll() throws Exception {
		 
		 mockMvc.perform(get("/brands/all"))
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
		 
		 mockMvc.perform(get("/brands/name?name=initTestBrand"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.type", is("SUCCESS")))
        .andExpect(jsonPath("$.message").isEmpty())
        .andExpect(jsonPath("$.data").isNotEmpty());
		 
		 mockMvc.perform(get("/brands/name?name=thisshouldnotexist"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.type", is("SUCCESS")))
        .andExpect(jsonPath("$.message").isEmpty())
        .andExpect(jsonPath("$.data").isEmpty());
	 }
	
	@Test
	 public void testCreateBrand() throws Exception {
		 
		 String testJson = "{\"name\" : \"brand value that does not exists\"}";
		 
		 mockMvc.perform(post("/brands/create").content(testJson).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.type", is("SUCCESS")))
        .andExpect(jsonPath("$.message").isEmpty())
        .andExpect(jsonPath("$.data").isNotEmpty());
		 
		 //Trying to create again with same name => error
		 mockMvc.perform(post("/brands/create").content(testJson).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.type", is("ERROR")))
        .andExpect(jsonPath("$.message").isNotEmpty())
        .andExpect(jsonPath("$.data").isEmpty());
	 }
	
	@Test
	 public void testDeleteBrand() throws Exception {
		 
		 String testJson = "{\"name\" : \"brand for delete\"}";
		 
		 mockMvc.perform(post("/brands/create").content(testJson).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.type", is("SUCCESS")))
        .andExpect(jsonPath("$.message").isEmpty())
        .andExpect(jsonPath("$.data").isNotEmpty());
		 
		 // Deleting article
		 mockMvc.perform(post("/brands/delete").content(testJson).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.type", is("SUCCESS")))
        .andExpect(jsonPath("$.message").isEmpty())
        .andExpect(jsonPath("$.data").isEmpty());
		 
		 // Article should not exist
		 mockMvc.perform(get("/brands/name?name=brand for delete"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.type", is("SUCCESS")))
        .andExpect(jsonPath("$.message").isEmpty())
        .andExpect(jsonPath("$.data").isEmpty());
	 }
	
	@After 
	 public void cleanUp() {
		 
		 Brand brand1 = brandRepository.findByName("initTestBrand");
		 if(brand1 != null)
		 {
			 brandRepository.delete(brand1);
		 }
		 
		 Brand brand2 = brandRepository.findByName("brand value that does not exists");
		 if(brand2 != null)
		 {
			 brandRepository.delete(brand2);
		 }
	 }
}
