package com.bfs.contact.controller;


import com.bfs.contact.domain.PersonalContact;
import com.bfs.contact.repository.PersonalContactDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {


    @Autowired
    private PersonalContactDAO personalContactDAO;

    @GetMapping("/findByUserId")
    public ResponseEntity<PersonalContact> findByUserId(@RequestParam String userId){
        PersonalContact personalContact = personalContactDAO.findPersonalContactByUserId(userId);

        if(personalContact == null)
            return new ResponseEntity("temporarily No Profile", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(personalContact, HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<PersonalContact> save(@RequestBody PersonalContact personalContact){
        List<PersonalContact> list = personalContactDAO.findAll();
        int size = list.size();
        personalContact.setId(String.valueOf(++size));
        PersonalContact personalContact1 = personalContactDAO.save(personalContact);
        return new ResponseEntity<>(personalContact1,HttpStatus.OK);

    }



}
