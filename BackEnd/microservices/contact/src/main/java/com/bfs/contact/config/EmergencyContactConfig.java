//package com.bfs.contact.config;
//
//
//        import com.bfs.contact.domain.EmergencyContact;
//        import com.bfs.contact.repository.EmergencyContactDAO;
//        import org.springframework.boot.CommandLineRunner;
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
////@EnableMongoRepositories(basePackageClasses = EmergencyContact.class)
//@Configuration
//public class EmergencyContactConfig {
//    @Bean("emergencyContact")
//    CommandLineRunner commandLineRunner(EmergencyContactDAO emergencyContactDAO) {
//        return strings -> {
//            emergencyContactDAO.deleteAll();
//            emergencyContactDAO.save(new EmergencyContact("1", "1", "New York", "Tim Lee", "2022212222", "Jessica Garcia", "2022212221"));
//            emergencyContactDAO.save(new EmergencyContact("2", "2", "New Jersey", "Winston Lee", "7537890001", "Jade Williams", "7537890002"));
//        };
//
//    }
//}
