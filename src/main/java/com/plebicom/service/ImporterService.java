package com.plebicom.service;

import com.plebicom.persistence.entity.Importer;
import com.plebicom.persistence.enums.ImportStatus;
import com.plebicom.persistence.repository.ImporterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImporterService {

    @Autowired
    private ImporterRepository importerRepository;

    public Importer updateImporter(Importer importer){
        return importerRepository.save(importer);
    }

    public Importer updateImporter(Importer importer, ImportStatus status, String log){

        importer.setLog(log);
        return updateImporter(importer, status);
    }

    public Importer updateImporter(Importer importer, ImportStatus status){

        importer.setStatus(status);
        return updateImporter(importer);
    }
}
