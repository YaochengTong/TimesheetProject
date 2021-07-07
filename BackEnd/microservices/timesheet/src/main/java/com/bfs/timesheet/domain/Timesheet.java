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
    private Double totalBillingHour;
    private Double totalCompensatedHour;
    private String submissionStatus;
    private String approvalStatus;
    private String comment;



    private List<Day> days;// template better than days?
    //B2, B3, B3, B5 can be as a template
//    private String templateId;

    private Integer floatingLeft;
    private Integer holidayLeft;

}
