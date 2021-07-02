package com.example.authserver.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class Test {

    @Id
    @ApiModelProperty(notes = "The database generated Test domain ID")
    private String id;

    @ApiModelProperty(notes = "Test first name", required = true)
    private String firstName;
    @ApiModelProperty(notes = "Test last name")
    private String lastName;
}
