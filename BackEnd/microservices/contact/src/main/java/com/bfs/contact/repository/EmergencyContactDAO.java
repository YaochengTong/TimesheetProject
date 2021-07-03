package com.bfs.contact.repository;

import com.bfs.contact.domain.EmergencyContact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmergencyContactDAO extends MongoRepository<EmergencyContact,String> {

    //this parameter name must be the same as field we just define in domain.
    EmergencyContact findEmergencyContactByPersonalContactId(String personalContactId);
    EmergencyContact findEmergencyContactByName1(String name1);
}
