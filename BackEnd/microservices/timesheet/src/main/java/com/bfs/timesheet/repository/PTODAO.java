package com.bfs.timesheet.repository;

import com.bfs.timesheet.domain.PTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PTODAO extends MongoRepository<PTO,String> {
    List<PTO> findAllByUserId(String userId);
    PTO findByUserIdAndYear(String userId, Integer year);
}
