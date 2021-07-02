package com.bfs.timesheet.domain;

import lombok.*;
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
    private Integer floatingCount;
    private Integer vacationCount;
}
