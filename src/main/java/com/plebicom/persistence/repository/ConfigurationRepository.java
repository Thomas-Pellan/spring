package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Integer> {

	Configuration findByKey(String key);

}
