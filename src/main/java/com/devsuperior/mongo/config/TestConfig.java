package com.devsuperior.mongo.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devsuperior.mongo.models.entities.User;
import com.devsuperior.mongo.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private UserRepository userRepository;
	@PostConstruct
	public void init() {
		
		userRepository.deleteAll(); //zerar o banco
		
		//criar um usuario para teste
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex =  new User(null, "Alex Brown", "alex@gmail.com");
		User bob =  new User(null, "Bob Brown", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
	}

}
