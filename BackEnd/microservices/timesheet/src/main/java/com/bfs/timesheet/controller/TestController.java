package com.bfs.timesheet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testTimeSheet")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity test1(){
        return ResponseEntity.ok("test from timesheet");
    }
}
