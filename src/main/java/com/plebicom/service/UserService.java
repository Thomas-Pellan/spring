package com.plebicom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plebicom.persistence.entity.User;
import com.plebicom.persistence.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
    
    public User findUserByUsername(String username) {
        		
        return userRepository.findByUsername(username);
    }
}
