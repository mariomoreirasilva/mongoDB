package com.devsuperior.mongo.repositories;

import java.time.Instant;
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
	List<Post> procurarTexto(String texto);
	
	//  operador e { $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }
	// maior       { field: { $gte: value } }
	//menor        { field: { $lte: value } }
	//operador or { $or: [ { <expression1> }, { <expression2> }, ... , { <expressionN> } ] }
	//tem texto titulo, ou corpo ou comentários
	//{ $or: [{ 'title': { $regex: ?0, $options: 'i' } } , { 'body': { $regex: ?0, $options: 'i' } },{ 'comments.text': { $regex: ?0, $options: 'i' } } ] }
	//completo com and e or
	//{ $and: [ { field: { $gte: value } }, { field: { $lte: value } } , { $or: [{ 'title': { $regex: ?0, $options: 'i' } } , { 'body': { $regex: ?0, $options: 'i' } },{ 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }
	
	@Query("{ $and: [ { 'moment': { $gte: ?1 } }, { 'moment': { $lte: ?2 } } , { $or: [{ 'title': { $regex: ?0, $options: 'i' } } , { 'body': { $regex: ?0, $options: 'i' } },{ 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> bucaCompleta(String texto, Instant dataIni, Instant dataFim);
	
	List<Post> findByTitleContainingIgnoreCase(String texto);

}
