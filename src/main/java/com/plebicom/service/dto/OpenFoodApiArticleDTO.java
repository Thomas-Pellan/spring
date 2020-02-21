package com.plebicom.service.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

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

    @SerializedName("additives_old_tags")
    private List<String> additivesOldTags = new ArrayList<>();
}
