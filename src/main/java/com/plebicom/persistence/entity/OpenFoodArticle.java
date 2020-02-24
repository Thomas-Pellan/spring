package com.plebicom.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Table(name = "open_article")
public class OpenFoodArticle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ean_code", nullable = false)
    private String eanCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified;

    @Column(name = "nutrition_grade")
    private String nutritionGrade;
}
