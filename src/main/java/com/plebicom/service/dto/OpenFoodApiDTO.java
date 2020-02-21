package com.plebicom.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class OpenFoodApiDTO implements Serializable {

    private List<OpenFoodApiArticleDTO> articles = new ArrayList<>();
}
