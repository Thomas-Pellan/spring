package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.Article;
import com.plebicom.persistence.entity.Configuration;
import org.springframework.data.repository.CrudRepository;

public interface ConfigurationRepository extends CrudRepository<Configuration, Integer> {

	Configuration findByKey(String key);

}
