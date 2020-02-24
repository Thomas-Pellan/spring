package com.plebicom.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.plebicom.persistence.entity.AccessRight;
import com.plebicom.persistence.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRightRepository extends CrudRepository<AccessRight, Integer> {

	AccessRight findByUser(User user);

}
