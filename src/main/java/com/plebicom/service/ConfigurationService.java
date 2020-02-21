package com.plebicom.service;

import com.plebicom.persistence.entity.Configuration;
import com.plebicom.persistence.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository confRepository;

    public String getConfigurationValueByKeyAsString(String key) {

        Configuration conf = confRepository.findByKey(key);
        return conf == null ? null : conf.getValue();
    }
}
