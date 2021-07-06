package com.bfs.contact.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "emergencyContact")
public class EmergencyContact {
    @Id
    private String id;


    private String personalContactId;

    private String name1;
    private String number1;

    private String name2;
    private String number2;

}
