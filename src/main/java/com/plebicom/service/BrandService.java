package com.plebicom.service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plebicom.persistence.entity.Brand;
import com.plebicom.persistence.repository.BrandRepository;
import com.plebicom.site.dto.ArticleDTO;
import com.plebicom.site.dto.BrandDTO;
import com.plebicom.site.factory.BrandFactory;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private BrandFactory brandFactory;
	
	public Iterable<BrandDTO> getAllBrands() {
			
		Iterable<Brand> brands = brandRepository.findAll();
		
		return StreamSupport.stream(brands.spliterator(), false).map(brand -> brandFactory.createDTOFromEntity(brand))
			    .collect(Collectors.toList());
	}

	public BrandDTO getBrandByName(String name) {
		
		Brand brand = brandRepository.findByName(name);
		
		if(brand == null)
		{
			return null;
		}
		
		return brandFactory.createDTOFromEntity(brand);
	}
	
	public Object createBrand(BrandDTO dto) {
		
		if(dto == null || StringUtils.isBlank(dto.getName()))
		{
			return "No data";
		}
		
		Brand existing = brandRepository.findByName(dto.getName());
		
		if(existing != null)
		{
			return String.format("Brand already exists with the name %s", dto.getName());
		}
		
		Brand newBrand = brandRepository.save(brandFactory.createEntityFromDTO(dto));
		
		return brandFactory.createDTOFromEntity(newBrand);
	}
	
	public Object removeBrand(ArticleDTO dto) {
		
		if(dto == null)
		{
			return "No data";
		}
		
		Brand existing = brandRepository.findByName(dto.getName());
		
		if(existing == null)
		{
			return String.format("Article does not exists with the name %s", dto.getName());
		}
		
		brandRepository.delete(existing);
		
		return null;
	}
}
