package com.example.authserver.controller;

import com.example.authserver.domain.Test;
import com.example.authserver.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class ExampleController {

    @Autowired
    private TestRepository repository;

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
}
