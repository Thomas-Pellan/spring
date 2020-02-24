package com.plebicom.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "open_ingredient_ranking")
public class OpenFoodIngredientRanking {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ranking")
    private Integer rank;

    @ManyToOne
    @JoinColumn(name="open_article")
    private OpenFoodArticle article;

    @ManyToOne
    @JoinColumn(name="open_ingredient")
    private OpenFoodIngredient ingredient;
}
