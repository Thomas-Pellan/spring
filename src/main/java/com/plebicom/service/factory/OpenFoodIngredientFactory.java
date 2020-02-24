package com.plebicom.service.factory;

import com.plebicom.persistence.entity.OpenFoodIngredient;
import com.plebicom.persistence.repository.OpenFoodIngredientRepository;
import com.plebicom.service.dto.OpenFoodApiIngredientDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenFoodIngredientFactory {

    private static final String YES = "yes";

    @Autowired
    private OpenFoodIngredientRepository openFoodIngredientRepository;

    @Autowired
    private LocaleFactory localeFactory;

    public OpenFoodIngredient createOrMergeOpenApiIngredientFromDto(OpenFoodApiIngredientDTO dto){

        if(dto == null){
            return null;
        }

        String[] idAndLocale = dto.getId().split(":");

        //Id should be locale:id (eg : en:garlic)
        if(idAndLocale.length < 2 ){
            return null;
        }

        String id = idAndLocale[1];
        OpenFoodIngredient ingredient = new OpenFoodIngredient();
        OpenFoodIngredient existing = openFoodIngredientRepository.findByCode(id);
        if(dto.getId() != null && existing != null){
            ingredient = existing;
        }
        else
        {
            ingredient.setCode(id);
        }

        ingredient.setLocale(localeFactory.createOrMergeLocaleFromDto(idAndLocale[0]));
        ingredient.setFromPalmOil(YES.equals(dto.getFromPalmOil()) ? Boolean.TRUE : Boolean.FALSE);
        ingredient.setVegan(YES.equals(dto.getVegan()) ? Boolean.TRUE : Boolean.FALSE);
        ingredient.setVegetarian(YES.equals(dto.getVegetarian()) ? Boolean.TRUE : Boolean.FALSE);
        ingredient.setLabel(dto.getLabel());

        return openFoodIngredientRepository.save(ingredient);
    }
}
