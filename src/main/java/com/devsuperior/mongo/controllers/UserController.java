package com.devsuperior.mongo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.mongo.models.dto.UserDTO;
import com.devsuperior.mongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findall(){
		List<UserDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findaById(@PathVariable String id){
		UserDTO userDto = service.findById(id);
		return ResponseEntity.ok().body(userDto);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> inserir(@RequestBody UserDTO obj){
		obj = service.inserir(obj);
		//macete retornar codigo 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);	
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<UserDTO> atualizar(@PathVariable String id, @RequestBody UserDTO obj){
		obj = service.atualizar(id, obj);
		return ResponseEntity.ok().body(obj);
		
	}
	
	

}
