package com.plebicom.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.plebicom.persistence.entity.Brand;

public interface BrandRepository extends CrudRepository<Brand, Integer> {

	public Brand findByName(String name);

}
