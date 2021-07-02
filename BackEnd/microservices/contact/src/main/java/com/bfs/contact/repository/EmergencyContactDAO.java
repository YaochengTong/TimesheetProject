package com.bfs.contact.repository;

import com.bfs.contact.domain.EmergencyContact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmergencyContactDAO extends MongoRepository<EmergencyContact,String> {

    EmergencyContact findEmergencyContactByPersonId(String personId);

    EmergencyContact findEmergencyContactByName1(String name1);
}
