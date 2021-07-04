package com.bfs.summary.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Day {

    public Day(String startTime, String endTime, Double totalHours, Boolean floating, Boolean vacation,
               Boolean holiday) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalHours = totalHours;
        this.floating = floating;
        this.vacation = vacation;
        this.holiday = holiday;
    }

    String day; //we need to define it manually(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday)
    String date;
    String startTime;
    String endTime;
    Double totalHours;
    Boolean floating;
    Boolean vacation;
    Boolean holiday;
}
