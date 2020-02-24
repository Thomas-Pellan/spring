package com.plebicom.service;

import com.plebicom.persistence.entity.Importer;
import com.plebicom.persistence.entity.ImporterFile;
import com.plebicom.persistence.enums.FileImportStatus;
import com.plebicom.persistence.enums.ImportStatus;
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
public class OpenApiImporterService {

    private static final String OPENAPI_FILE_SEPARATOR = "\n";

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private OpenApiArticleFactory openApiArticleFactory;

    @Autowired
    private ImporterService importerService;

    @Autowired
    private ImporterFileService importerFileService;

    @Autowired
    private QueryUtil queryUtil;

    public void ImportOpenArticles(){

        //Init import information
        Importer importer = importerService.updateImporter(new Importer());

        //Get files from the open api server
        String[] fileList = getOpenApiFileList(importer);

        if(fileList == null){
            return;
        }

        String urlFileQuery = configurationService.getConfigurationValueByKeyAsString("openFood_url_file_query");
        if(StringUtils.isBlank(urlFileQuery)){
            importerService.updateImporter(importer, ImportStatus.FAILED, "No openFood_url_file_query configuration found");
            return;
        }

        //Download each file and import it's data
        OpenFoodApiDTO dto = new OpenFoodApiDTO();
        importer = importerService.updateImporter(importer, ImportStatus.DOWNLOAD);
        for(String fileName : fileList){

            ImporterFile importerFile = importerFileService.updateImporterFile(new ImporterFile(fileName, importer));
            importOpenApiArticleFile(urlFileQuery, fileName, importerFile, dto);
            importerFileService.updateImporterFile(importerFile);
        }

        //Persisting the articles
        importer = importerService.updateImporter(importer, ImportStatus.IMPORT);
        openApiArticleFactory.createOrMergeOpenApiArticleFromDto(dto.getArticles());
        importerService.updateImporter(importer, ImportStatus.SUCCESS);
    }

    private void importOpenApiArticleFile(String urlFileQuery, String fileName, ImporterFile importerFile, OpenFoodApiDTO apiDto){

        importerFile.setStatus(FileImportStatus.DOWNLOAD);

        //Get the file, unzip and parse it's elements
        List<String> fileContentList;
        try (InputStream is = new GZIPInputStream(queryUtil.getFileData(urlFileQuery + fileName)))
        {
            String fileContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            importerFile.setMd5Checksum(DigestUtils.md5Hex(fileContent));
            fileContentList = Arrays.asList(fileContent.split(OPENAPI_FILE_SEPARATOR));
        } catch (IOException e) {
            importerFile.setStatus(FileImportStatus.FAIL);
            log.error(String.format("getFileList : error while getting %s content, data not imported", fileName), e);
            return;
        }

        //Empty file or no data, skip
        if(CollectionUtils.isEmpty(fileContentList)){
            importerFile.setStatus(FileImportStatus.FAIL);
            return;
        }

        /**Don't re import if :
            - Same md5 found on a file with same file name
            - the previous import file status is OK
        **/
        List<ImporterFile> sameFile = importerFileService.getImportedFileWithName(fileName);
        sameFile.removeIf(file -> {
            return file == null
                    || file.getImporter() == importerFile.getImporter()
                    || !importerFile.getMd5Checksum().equals(file.getMd5Checksum())
                    || file.getStatus() != FileImportStatus.SUCCESS;
        });
        if(!CollectionUtils.isEmpty(sameFile)){
            importerFile.setStatus(FileImportStatus.ALREADY_IMPORTED);
            return;
        }

        importerFile.setStatus(FileImportStatus.IMPORT);
        //Parse the line content to get usable Objects
        List<OpenFoodApiArticleDTO> newArticles = fileContentList
                .stream()
                .map(line -> openApiArticleFactory.getOpenFoodApiArticleFromString(line))
                .filter(o -> o != null)
                .collect(Collectors.toList());
        importerFile.setStatus(FileImportStatus.SUCCESS);
        apiDto.getArticles().addAll(newArticles);
    }

    private String[] getOpenApiFileList(Importer importer) {

        String urlList = configurationService.getConfigurationValueByKeyAsString("openFood_url_list");
        if (StringUtils.isBlank(urlList)) {
            importerService.updateImporter(importer, ImportStatus.FAILED, "Missing url configuration found");
            return null;
        }

        String files = queryUtil.getDataAsString(urlList);
        if (StringUtils.isBlank(files)) {
            importerService.updateImporter(importer, ImportStatus.FAILED, "Got no files from open food api");
            return null;
        }

        return files.split(OPENAPI_FILE_SEPARATOR);
    }
}
