package jvdb18.treestagramapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import jvdb18.treestagramapi.model.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

//    @Query("SELECT user FROM User user WHERE user.username = :username")
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

}
