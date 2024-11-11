package com.devsuperior.mongo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.mongo.models.dto.PostDTO;
import com.devsuperior.mongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
	
	@Autowired
	private PostService service;	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PostDTO> findaById(@PathVariable String id){
		PostDTO postDto = service.findById(id);
		return ResponseEntity.ok().body(postDto);
	}
	
	@GetMapping(value="/titlesearch")
	public ResponseEntity<List<PostDTO>> buscaPorTitulo(@RequestParam(value = "text", defaultValue = "") String texto){
		
		List<PostDTO> lista = service.procurarPorTexto(texto);
		return ResponseEntity.ok().body(lista);
	}

	
	@GetMapping(value="/fullsearch")
	public ResponseEntity<List<PostDTO>> buscaCompleta(
			@RequestParam(value = "text", defaultValue = "") String texto,
			@RequestParam(value = "start", defaultValue = "") String dataIni,
			@RequestParam(value = "end", defaultValue = "") String dataFim){
		
		List<PostDTO> lista = service.buscaCompleta(texto, dataIni, dataFim);
		return ResponseEntity.ok().body(lista);
	}

	
}
