package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.OpenFoodIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenFoodIngredientRepository extends CrudRepository<OpenFoodIngredient, Integer> {

    OpenFoodIngredient findByCode(String code);
}
