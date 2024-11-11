package com.devsuperior.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.mongo.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//comandos estão na web https://www.mongodb.com/pt-br/docs/manual/reference/operator/query/regex/
	//?0 significa pegar o primeiro parâmetro da função, que no caso é texto
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> ProcurarTexto(String texto);
	
	List<Post> findByTitleContainingIgnoreCase(String texto);

}
