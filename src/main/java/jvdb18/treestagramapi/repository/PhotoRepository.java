package jvdb18.treestagramapi.repository;

import jvdb18.treestagramapi.model.entities.Photo;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface PhotoRepository extends MongoRepository<Photo, String> {
    
}
