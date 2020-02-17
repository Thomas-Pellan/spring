package com.plebicom.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plebicom.service.BrandService;
import com.plebicom.site.dto.BrandDTO;

@Controller
@RequestMapping(path="/brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;

	@PostMapping(path="/create")
	public @ResponseBody
	ResponseEntity createBrand (@RequestBody BrandDTO brand) {
		return ResponseEntity.ok(brandService.createBrand(brand));
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody ResponseEntity deleteBrand (@RequestBody BrandDTO brand) {
		brandService.removeBrand(brand);
		return ResponseEntity.ok(null);
	}

	@GetMapping(path="/all")
	public @ResponseBody ResponseEntity getAllBrands() {
		return ResponseEntity.ok(brandService.getAllBrands());
	}
	
	@GetMapping(path="/name")
	public @ResponseBody ResponseEntity getBrandByName(@RequestParam String name) {
		return ResponseEntity.ok(brandService.getBrandByName(name));
	}
}
