package com.bfs.timesheet.repository;

import com.bfs.timesheet.domain.Holidays;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HolidaysDAO extends MongoRepository<Holidays, String> {

    Holidays findByYear(Integer years);

}
