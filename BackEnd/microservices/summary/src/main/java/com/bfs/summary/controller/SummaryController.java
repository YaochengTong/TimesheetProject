package com.bfs.summary.controller;

import com.bfs.summary.domain.Timesheet;
import com.bfs.summary.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Summary")
public class SummaryController {

    @Autowired
    private SummaryRepository summaryRepository;

    @GetMapping("/")
    @ResponseBody
    public List<Timesheet> getAllSummary() {

        List<Timesheet> list = summaryRepository.findAll();

        return list;
    }

    @PostMapping("/")
    public ResponseEntity addSummary() {
        return ResponseEntity.ok("hello");
    }
}
