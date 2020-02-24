package com.plebicom.service.factory;

import com.plebicom.persistence.entity.Locale;
import com.plebicom.persistence.repository.LocaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocaleFactory {

    @Autowired
    private LocaleRepository localeRepository;

    public Locale createOrMergeLocaleFromDto(String label){

        if(StringUtils.isBlank(label)){
            return null;
        }

        Locale existing = localeRepository.findByLabel(label);
        if(existing != null)
        {
            return existing;
        }

        Locale locale = new Locale();
        locale.setLabel(label);

        return localeRepository.save(locale);
    }
}
