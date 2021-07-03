package com.bfs.contact.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "personalContact")
public class PersonalContact {



    @Id
    private String id;

    private String userId;
    private String number;
    private String email;
    private String addressId;



    private String emergencyContactId;
}
