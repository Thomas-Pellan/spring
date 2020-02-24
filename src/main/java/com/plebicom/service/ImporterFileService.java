package com.plebicom.service;

import com.plebicom.persistence.entity.ImporterFile;
import com.plebicom.persistence.enums.FileImportStatus;
import com.plebicom.persistence.repository.ImporterFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ImporterFileService {

    @Autowired
    private ImporterFileRepository importerFileRepository;

    public ImporterFile updateImporterFile(ImporterFile importerFile){
        return importerFileRepository.save(importerFile);
    }

    public ImporterFile updateImporterFile(ImporterFile importerFile, FileImportStatus status){

        importerFile.setStatus(status);
        return updateImporterFile(importerFile);
    }

    public List<ImporterFile> getImportedFileWithName(String fileName){

        return importerFileRepository.findByFileName(fileName);
    }
}
