package com.example.authserver.repository;

import com.example.authserver.domain.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends MongoRepository<Test, String> {

    Optional<Test> findByFirstName(String firstName);

    List<Test> findByLastName(String lastName);

    @Query(value = "{'lastName': ?0}", fields = "{'firstName': 1}")
    List<Test> searchTestByLastName(String lastName);
}
