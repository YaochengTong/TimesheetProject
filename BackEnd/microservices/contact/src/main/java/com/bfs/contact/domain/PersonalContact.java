package com.bfs.contact.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "personalContact")
public class PersonalContact {



    @Id
    private String id;

    private String number;
    private String email;
    private String address_id;
    private String emergencyContact_id;
}
