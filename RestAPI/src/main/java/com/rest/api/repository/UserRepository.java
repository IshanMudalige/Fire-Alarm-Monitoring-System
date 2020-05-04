package com.rest.api.repository;

import com.rest.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByUsernameAndPassword(String username,String password);
}
