package com.devsuperior.mongo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.mongo.models.dto.PostDTO;
import com.devsuperior.mongo.models.entities.Post;
import com.devsuperior.mongo.repositories.PostRepository;
import com.devsuperior.mongo.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
		
	
	public PostDTO findById(String id) {
		
		Post entity = recuperaPostPorId(id);
		
		return new PostDTO(entity);				
	}
	
	private Post recuperaPostPorId(String id) {
			Optional<Post> obj = repository.findById(id);		
			return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado."));		
	}
	
	public List<PostDTO> procurarPorTexto(String texto){
		List<Post> lista = repository.findByTitleContainingIgnoreCase(texto);
		return lista.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}

}
