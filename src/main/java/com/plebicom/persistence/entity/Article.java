package com.plebicom.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="brand")
    private Brand brand;

    @Column(name = "description", nullable=true)
    private String description;
    
}
