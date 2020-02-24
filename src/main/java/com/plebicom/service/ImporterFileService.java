package com.plebicom.service;

import com.plebicom.persistence.entity.Importer;
import com.plebicom.persistence.entity.ImporterFile;
import com.plebicom.persistence.enums.FileImportStatus;
import com.plebicom.persistence.enums.ImportStatus;
import com.plebicom.persistence.repository.ImporterFileRepository;
import com.plebicom.persistence.repository.ImporterRepository;
import com.plebicom.service.dto.OpenFoodApiArticleDTO;
import com.plebicom.service.dto.OpenFoodApiDTO;
import com.plebicom.service.factory.OpenApiArticleFactory;
import com.plebicom.util.QueryUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

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
