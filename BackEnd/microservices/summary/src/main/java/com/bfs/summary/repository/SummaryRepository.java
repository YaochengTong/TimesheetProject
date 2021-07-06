package com.bfs.summary.repository;

import com.bfs.summary.domain.Timesheet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SummaryRepository extends MongoRepository<Timesheet, String> {

}
