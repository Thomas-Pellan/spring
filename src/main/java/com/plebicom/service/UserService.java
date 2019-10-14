package com.plebicom.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.plebicom.persistence.entity.User;
import com.plebicom.persistence.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Objects.requireNonNull(username);
        
        return userRepository.findUserWithName(username);
    }
    
    public User createUser(User user) {
    	
    	return userRepository.save(user);
    }
}
