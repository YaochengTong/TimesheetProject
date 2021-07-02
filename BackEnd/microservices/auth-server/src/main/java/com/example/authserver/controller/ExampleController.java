package com.example.authserver.controller;

import com.example.authserver.domain.Test;
import com.example.authserver.domain.User;
import com.example.authserver.repository.TestRepository;
import com.example.authserver.repository.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class ExampleController {

    @Autowired
    private TestRepository repository;

    @Autowired
    private UserDAO userDAO;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/test")
    public ResponseEntity test1(){
        return ResponseEntity.ok("test from service 1");
    }

    @GetMapping("/mongoTest")
    public ResponseEntity test2() {

        repository.save(Test.builder().firstName("Alice").lastName("Smith").build());
        repository.save(Test.builder().firstName("Bob").lastName("Smith").build());

        return ResponseEntity.ok("test from service 2");
    }

    /////////////////////////   User    ////////////////////////////
    //Test user save
    @PostMapping("/mongoUserTest")
    public ResponseEntity<User> testUser(@RequestBody User user) {

        User user1= userDAO.save(user);
        return new ResponseEntity(user1, HttpStatus.CREATED);
    }
    //Test find user by email
    @GetMapping("/mongoUserTest/{Email}")
    public ResponseEntity<User> testUserByName(@PathVariable("Email") String email) {

        User user1= userDAO.findByEmail(email);
        logger.debug(user1.toString());
        return new ResponseEntity(user1, HttpStatus.OK);
    }

    /////////////////////////   User    ////////////////////////////





}
