package com.bfs.timesheet.repository;

import com.bfs.timesheet.domain.Timesheet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimesheetDAO extends MongoRepository<Timesheet,String> {

    List<Timesheet> findAllByUserId(String userId);
    Timesheet findByUserIdAndWeekEnding(String userId, String weekEnding);
}
