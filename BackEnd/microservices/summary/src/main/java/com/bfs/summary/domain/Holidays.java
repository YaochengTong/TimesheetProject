package com.bfs.summary.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "holidays")
public class Holidays {
    
    @Id
    private String id;
    private Integer year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getHoliday() {
        return holiday;
    }

    public void setHoliday(List<String> holiday) {
        this.holiday = holiday;
    }

    private List<String> holiday; //we need to define it manually
    //Each date corresponds to the holiday instead of name of holiday
}
