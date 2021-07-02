package com.bfs.contact.controller;

import com.bfs.contact.domain.Address;
import com.bfs.contact.repository.AddressDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testDomain")
public class TestDomain {

    @Autowired
    private AddressDAO addressDAO;

    private Logger logger = LoggerFactory.getLogger(getClass());

    //Test save address
    @PostMapping("/saveAddress")
    public ResponseEntity<Address> testUser(@RequestBody Address address) {

        Address address1= addressDAO.save(address);
        return new ResponseEntity(address1, HttpStatus.CREATED);
    }

    //Test find all addresses
    @GetMapping("/findAll")
    public ResponseEntity<List<Address>> findAll() {

        List<Address> list= addressDAO.findAll();
        logger.debug(list.toString());
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
