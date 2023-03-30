package jvdb18.treestagramapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import jvdb18.treestagramapi.model.entities.Post;

public interface PostRepository extends MongoRepository<Post, String>{
    
}
