package com.bfs.contact.repository;

import com.bfs.contact.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressDAO extends MongoRepository<Address,String> {
    Address findAddressById(String id);
    Address findAddressByHomeAddress(String homeAddress);
}
