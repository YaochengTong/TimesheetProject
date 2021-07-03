package com.bfs.timesheet.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "timesheet")
public class Timesheet {
    @Id
    private String id;

    private String userId;

    private String weekEnding;
    private Integer totalBillingHour;
    private Integer totalCompensatedHour;
    private String submissionStatus;
    private String approvalStatus;
    private String comment;



    private List<Day> days;
//    private String templateId;

    private Integer floatingLeft;
    private Integer holidayLeft;

}