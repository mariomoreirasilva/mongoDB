package com.devsuperior.mongo.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.mongo.models.dto.UserDTO;
import com.devsuperior.mongo.models.entities.User;
import com.devsuperior.mongo.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll(){
		
		List<User> list = repository.findAll();
		
		return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}

}
