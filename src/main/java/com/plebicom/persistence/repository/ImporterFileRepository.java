package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.ImporterFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImporterFileRepository extends CrudRepository<ImporterFile, Integer> {

    List<ImporterFile> findByFileName(String fileName);
}
