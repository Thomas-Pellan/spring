package com.plebicom.persistence.entity;

import com.plebicom.persistence.enums.FileImportStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "importer_file")
@AllArgsConstructor
@NoArgsConstructor
public class ImporterFile {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String fileName;

    @Column(name = "status", nullable = false)
    private FileImportStatus status;

    @ManyToOne
    @JoinColumn(name="importer", nullable = false)
    private Importer importer;

    @Column(name = "md5_checksum")
    private String md5Checksum;

    public ImporterFile(String fileName, Importer importer){

        this.setStatus(FileImportStatus.NEW);
        this.setFileName(fileName);
        this.setImporter(importer);
    }
}
