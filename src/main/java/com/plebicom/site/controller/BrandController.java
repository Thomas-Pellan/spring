package com.plebicom.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plebicom.service.BrandService;
import com.plebicom.site.dto.BrandDTO;
import com.plebicom.site.dto.ResponseDTO;
import com.plebicom.site.factory.ResponseFactory;

@Controller
@RequestMapping(path="/brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ResponseFactory responseFactory;

	@PostMapping(path="/create")
	public @ResponseBody ResponseDTO createArticle (@RequestBody BrandDTO brand) {

		Object returnValue = brandService.createBrand(brand);
		
		if(returnValue instanceof String)
		{
			return responseFactory.createErrorResponse((String) returnValue);
		}
		
		return responseFactory.createSuccessResponse(returnValue);
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody ResponseDTO deleteArticle (@RequestBody BrandDTO brand) {

		Object returnValue = brandService.removeBrand(brand);
		
		if(returnValue instanceof String)
		{
			return responseFactory.createErrorResponse((String) returnValue);
		}
		
		return responseFactory.createSuccessResponse(returnValue);
	}

	@GetMapping(path="/all")
	public @ResponseBody ResponseDTO getAllArticles() {
		return responseFactory.createSuccessResponse(brandService.getAllBrands());
	}
	
	@GetMapping(path="/name")
	public @ResponseBody ResponseDTO getArticleByName(@RequestParam String name) {
		return responseFactory.createSuccessResponse(brandService.getBrandByName(name));
	}
}
