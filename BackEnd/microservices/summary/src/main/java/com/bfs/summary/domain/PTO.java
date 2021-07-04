package com.bfs.summary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PTO")
public class PTO {

    @Id
    private String id;
    private String userId;
    private Integer year;
    private Integer floatingCount;//total floating days
    private Integer vacationCount;//total vacation days
}
