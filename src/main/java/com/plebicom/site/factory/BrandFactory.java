package com.plebicom.site.factory;

import org.springframework.stereotype.Service;

import com.plebicom.persistence.entity.Brand;
import com.plebicom.site.dto.BrandDTO;

@Service
public class BrandFactory {

	public BrandDTO createDTOFromEntity(Brand brand)
	{
		if(brand == null) 
		{
			return null;
		}
		
		BrandDTO dto = new BrandDTO();
		dto.setName(brand.getName());
		return dto;
	}
	
	public Brand createEntityFromDTO(BrandDTO dto)
	{
		if(dto == null) 
		{
			return null;
		}
		
		Brand brand = new Brand();
		brand.setName(dto.getName());
		return brand;
	}
}
