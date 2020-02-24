package com.plebicom.persistence.entity;

import com.plebicom.persistence.enums.ImportStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "importer")
public class Importer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "status", nullable = false)
    private ImportStatus status;

    @Column(name = "log")
    private String log;

    public Importer(){

        this.setStartDate(LocalDateTime.now());
        this.setStatus(ImportStatus.STARTED);
    }
}
