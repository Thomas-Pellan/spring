package com.plebicom.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="brand")
    private Brand brand;

    @Column(name = "description")
    private String description;
    
}
