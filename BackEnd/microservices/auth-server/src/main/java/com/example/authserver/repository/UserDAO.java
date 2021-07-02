package com.example.authserver.repository;

import com.example.authserver.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserDAO extends MongoRepository<User,String> {

    @Query("{'email':?0}")
    public User findByEmail(String userName);

}
