package com.bfs.contact.repository;

import com.bfs.contact.domain.PersonalContact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonalContactDAO extends MongoRepository<PersonalContact,String> {
    PersonalContact findPersonalContactById(String id);
    PersonalContact findPersonalContactByNumber(String number);

}
