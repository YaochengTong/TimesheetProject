package com.bfs.timesheet.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "holidays")
public class Holidays {
    @Id
    private String id;
    private Integer year;

    private List<String> holiday;
}
