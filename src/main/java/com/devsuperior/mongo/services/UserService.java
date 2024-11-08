package com.devsuperior.mongo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.mongo.models.dto.UserDTO;
import com.devsuperior.mongo.models.entities.User;
import com.devsuperior.mongo.repositories.UserRepository;
import com.devsuperior.mongo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll(){
		
		List<User> list = repository.findAll();
		
		return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	public UserDTO findById(String id) {
		
		User entity = recuperaUserPorId(id);
		
		return new UserDTO(entity);				
	}
	
	private User recuperaUserPorId(String id) {
			Optional<User> obj = repository.findById(id);		
			return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado."));		
	}
	
	public UserDTO inserir(UserDTO dto) {
		User entity = new User();
		copiaDTOparaEntidade(dto, entity);
		entity = repository.insert(entity);
		return new UserDTO(entity);
				
	}
	
	public UserDTO atualizar(String id, UserDTO dto) {
		
		User entity = recuperaUserPorId(id);
		copiaDTOparaEntidade(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	private void copiaDTOparaEntidade(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());		
	}

}
