package com.bfs.contact.config;


import com.bfs.contact.domain.EmergencyContact;
import com.bfs.contact.repository.EmergencyContactDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableMongoRepositories(basePackageClasses = EmergencyContact.class)
@Configuration
public class EmergencyContactConfig {
    @Bean("emergencyContact")
    CommandLineRunner commandLineRunner(EmergencyContactDAO emergencyContactDAO) {
        return strings -> {
            emergencyContactDAO.deleteAll();
            emergencyContactDAO.save(new EmergencyContact("1","1", "Tim Lee", "12345t3", "Hi Hello", "765432"));
            emergencyContactDAO.save(new EmergencyContact("2","2", "Winston Lee", "12345343", "Hi Hello", "435643"));
        };

    }
}
