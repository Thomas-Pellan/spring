package com.plebicom.service.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OpenFoodApiIngredientDTO implements Serializable {

    @SerializedName("vegetarian")
    private String vegetarian;

    @SerializedName("vegan")
    private String vegan;

    @SerializedName("id")
    private String id;

    @SerializedName("from_palm_oil")
    private String fromPalmOil;

    @SerializedName("has_sub_ingredients")
    private String hasSubIngredients;

    @SerializedName("ingredients")
    private List<OpenFoodApiIngredientDTO> subIngredients;

    @SerializedName("text")
    private String label;

    @SerializedName("rank")
    private Integer rank;
}
