package com.bfs.summary.controller;

import com.bfs.summary.domain.Timesheet;
import com.bfs.summary.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/AllSummary")
public class AllSummaryController {

    @Autowired
    private SummaryRepository summaryRepository;

    @GetMapping("/")
    @ResponseBody
    public List<Timesheet> getAllSummary() {

        List<Timesheet> list = summaryRepository.findAll();

        return list;
    }
}
