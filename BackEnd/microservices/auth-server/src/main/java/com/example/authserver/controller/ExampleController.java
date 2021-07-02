package com.example.authserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class ExampleController {

    @GetMapping("/test")
    public ResponseEntity test1(){
        return ResponseEntity.ok("test from service 1");
    }
}
