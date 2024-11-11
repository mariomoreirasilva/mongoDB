package com.devsuperior.mongo.services;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
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
		//List<Post> lista = repository.findByTitleContainingIgnoreCase(texto);
		List<Post> lista = repository.procurarTexto(texto);
		return lista.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	public List<PostDTO> buscaCompleta(String texto, String dataIni, String dataFim){	
		//vem do postMan como string. Converter String para moment(repository é moment)
		Instant dataInicial = converterParaMoment(dataIni, Instant.ofEpochSecond(0L));
		Instant dataFinal = converterParaMoment(dataFim, Instant.now());
		
		List<Post> lista = repository.bucaCompleta(texto, dataInicial, dataFinal);
		return lista.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}

	private Instant converterParaMoment(String dataOriginal, Instant dataAlternativa) {
		try {
			return Instant.parse(dataOriginal);
		}
		catch(DateTimeException e ) {
			return dataAlternativa;
		}
	}

}
