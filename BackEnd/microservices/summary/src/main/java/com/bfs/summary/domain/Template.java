package com.bfs.summary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "template")
public class Template {
    @Id
    private String id;

    private String userId;
    private List<Day> days;

    public Template() {
        this.days = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            if (i > 0 && i < 6){
                Day weekday = new Day("9:00","18:00",8.0,false,false,false);
                days.add(weekday);
            }
            else {
                Day weekend = new Day();
                days.add(weekend);
            }
        }
    }
}
