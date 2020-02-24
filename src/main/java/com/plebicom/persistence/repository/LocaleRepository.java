package com.plebicom.persistence.repository;

import com.plebicom.persistence.entity.Locale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleRepository extends CrudRepository<Locale, Integer> {

    Locale findByLabel(String label);
}
