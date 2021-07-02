package com.bfs.contact;

import com.bfs.contact.domain.Address;
import com.bfs.contact.domain.EmergencyContact;
import com.bfs.contact.domain.PersonalContact;
import com.bfs.contact.repository.AddressDAO;
import com.bfs.contact.repository.EmergencyContactDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = EmergencyContactDAO.class)
public class ContactApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactApplication.class, args);
    }

}
