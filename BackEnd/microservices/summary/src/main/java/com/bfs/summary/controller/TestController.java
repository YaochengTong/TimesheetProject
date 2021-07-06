package com.bfs.summary.controller;

import com.bfs.summary.domain.Timesheet;
import com.bfs.summary.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/testSummary")
public class TestController {

    @Autowired
    private SummaryRepository summaryRepository;

    @GetMapping("/test")
    public ResponseEntity test1(){
        return ResponseEntity.ok("test from summary");
    }

    @GetMapping("/testAllSummary")
    @ResponseBody
    public List<Timesheet> test2() {

        List<Timesheet> list = summaryRepository.findAll();

        return list;
    }

}
