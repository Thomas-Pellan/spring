package com.plebicom.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class OpenFoodApiArticle implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ean_code", nullable = false)
    private String eanCode;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;
}
