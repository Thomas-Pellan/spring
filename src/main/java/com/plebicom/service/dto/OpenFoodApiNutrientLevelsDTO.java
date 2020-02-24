package com.plebicom.service.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class OpenFoodApiNutrientLevelsDTO implements Serializable {


    @SerializedName("saturated-fat")
    private String saturatedFat;

    @SerializedName("fat")
    private String fat;

    @SerializedName("salt")
    private String salt;

    @SerializedName("sugars")
    private String sugars;

}
