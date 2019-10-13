package com.plebicom.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.plebicom.persistence.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsername(String username);
}
