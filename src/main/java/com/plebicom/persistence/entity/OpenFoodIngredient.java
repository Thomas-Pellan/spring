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
@Table(name = "open_ingredient")
public class OpenFoodIngredient {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "vegetarian")
    private Boolean vegetarian;

    @Column(name = "vegan")
    private Boolean vegan;

    @Column(name = "palm_oil")
    private Boolean fromPalmOil;

    @Column(name = "code")
    private String code;

    @Column(name = "label")
    private String label;

    @ManyToOne
    @JoinColumn(name="locale")
    private Locale Locale;
}
