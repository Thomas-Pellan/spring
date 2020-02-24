package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.Importer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImporterRepository extends CrudRepository<Importer, Integer> {

}
