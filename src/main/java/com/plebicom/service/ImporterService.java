package com.plebicom.service;

import com.plebicom.service.dto.OpenFoodApiArticleDTO;
import com.plebicom.service.dto.OpenFoodApiDTO;
import com.plebicom.service.factory.OpenApiArticleFactory;
import com.plebicom.site.exception.BusinessException;
import com.plebicom.util.QueryUtil;
import lombok.extern.slf4j.Slf4j;
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
public class ImporterService {

    private static final String OPENAPI_FILE_SEPARATOR = "\n";

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private OpenApiArticleFactory openApiArticleFactory;

    @Autowired
    private QueryUtil queryUtil;

    public String getFileList(){

        String urlList = configurationService.getConfigurationValueByKeyAsString("openFood_url_list");
        String urlFileQuery = configurationService.getConfigurationValueByKeyAsString("openFood_url_file_query");
        if(StringUtils.isBlank(urlList) || StringUtils.isBlank(urlFileQuery)){
            throw new BusinessException("Missing url configuration found");
        }

        String files = queryUtil.getDataAsString(urlList);
        if(StringUtils.isBlank(files)){
            throw new BusinessException("Got no file from open food api");
        }

        String[] fileList = files.split(OPENAPI_FILE_SEPARATOR);


        //Saving the file data and the file content in the database
        OpenFoodApiDTO dto = new OpenFoodApiDTO();
        for(String fileName : fileList){

            //Get the file, unzip and parse it's elements
            List<String> fileContentList = new ArrayList<>();
            try (InputStream is = new GZIPInputStream(queryUtil.getFileData(urlFileQuery + fileName)))
            {
                String fileContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                fileContentList = Arrays.asList(fileContent.split(OPENAPI_FILE_SEPARATOR));
            } catch (IOException e) {
                log.error(String.format("getFileList : error while getting %s content, data not imported", fileName), e);
            }

            //Empty file or no data, skip
            if(CollectionUtils.isEmpty(fileContentList)){
                continue;
            }

            //Parse the line content to get usable Objects
            List<OpenFoodApiArticleDTO> newArticles = fileContentList
                    .stream()
                    .map(line -> openApiArticleFactory.getOpenFoodApiArticleFromString(line))
                    .filter(o -> o != null)
                    .collect(Collectors.toList());
            dto.getArticles().addAll(newArticles);
        }

        //Persisting the articles
        openApiArticleFactory.createOrMergeOpenApiArticleFromDto(dto.getArticles());

        return null;
    }
}
