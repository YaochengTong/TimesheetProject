package com.bfs.summary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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



    @JsonIgnore
    private List<Day> days;
//    private String templateId;

    private Integer floatingLeft;
    private Integer holidayLeft;

}
