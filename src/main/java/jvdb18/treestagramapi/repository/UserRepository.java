package jvdb18.treestagramapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import jvdb18.treestagramapi.model.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

}
