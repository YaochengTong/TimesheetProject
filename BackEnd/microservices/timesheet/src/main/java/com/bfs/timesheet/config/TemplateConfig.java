package com.bfs.timesheet.config;

import com.bfs.timesheet.domain.Day;
import com.bfs.timesheet.domain.Template;
import com.bfs.timesheet.repository.TemplateDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//@EnableMongoRepositories(basePackageClasses = Template.class)
@Configuration
public class TemplateConfig {
    @Bean("template")
    CommandLineRunner commandLineRunner(TemplateDAO templateDAO) {
        return strings -> {
            templateDAO.deleteAll();
            List<Day> days = initializeDayList1();
            templateDAO.save(new Template("1", "1", days));
            templateDAO.save(new Template("2", "2", days));
        };
    }

    public List<Day> initializeDayList1(){
        List<Day> dayList1 = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            if (i > 0 && i < 6){
                Day weekday = new Day("10:00","18:00",8.0,false,false,false);
                dayList1.add(weekday);
            }
            else {
                Day weekend = new Day("N/A", "N/A", 0.0, false, false, false);
                dayList1.add(weekend);
            }
        }
        return dayList1;
    }
}
