package com.plebicom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;

import com.plebicom.persistence.entity.User;
import com.plebicom.service.UserService;

@SpringBootApplication(scanBasePackages = "com.plebicom")
public class Application implements CommandLineRunner {
	
	@Autowired
    UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Insert test user if it does not exists
		UserDetails user = userService.loadUserByUsername("admin");
		if(user == null)
		{
			User newUser = new User();
			newUser.setActive(true);
			newUser.setUsername("admin");
			newUser.setPassword("admin");
			userService.createUser(newUser);
		}
	}
}
