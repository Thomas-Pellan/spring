package com.plebicom.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "configuration")
public class Configuration {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "property_key", unique = true)
    private String key;

    @Column(name = "property_value")
    private String value;
}
