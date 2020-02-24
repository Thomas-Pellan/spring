package com.plebicom.service.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class OpenFoodApiArticleDTO implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("languages_hierarchy")
    private List<String> languagesHierarchy = new ArrayList<>();

    @SerializedName("last_modified_t")
    private String lastModified;

    @SerializedName("created_t")
    private String created;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("additives_old_tags")
    private List<String> additivesOldTags = new ArrayList<>();

    @SerializedName("_keywords")
    private List<String> keywords;

    @SerializedName("nutriments")
    private OpenFoodApiNutrimentsDTO nutriments;

    @SerializedName("ingredients_from_palm_oil_n")
    private Integer ingredientsFromPalmOil;

    @SerializedName("serving_size")
    private String servingSize;

    @SerializedName("nutrition_grade")
    private String nutritionGrade;

    @SerializedName("nutrient_levels")
    private OpenFoodApiNutrientLevelsDTO nutrientLevels;

    @SerializedName("ingredients_n")
    private Integer ingredientsNb;

    @SerializedName("ingredients")
    private List<OpenFoodApiIngredientDTO> ingredients;
}
