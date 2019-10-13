package com.plebicom.site.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ArticleDTO implements Serializable{

	private static final long serialVersionUID = 8020025990550735916L;

	private String name;

    private String description;
    
    private BrandDTO brand;
}
