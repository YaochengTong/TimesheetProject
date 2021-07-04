package com.bfs.timesheet.config;

import com.bfs.timesheet.domain.Day;
import com.bfs.timesheet.domain.Holidays;
import com.bfs.timesheet.domain.Timesheet;
import com.bfs.timesheet.repository.HolidaysDAO;
import com.bfs.timesheet.repository.TemplateDAO;
import com.bfs.timesheet.repository.TimesheetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//@EnableMongoRepositories(basePackageClasses = Timesheet.class)
@Configuration
public class TimeSheetConfig {

    @Autowired
    TemplateDAO templateDAO;

    @Autowired
    HolidaysDAO holidaysDAO;

    @Bean("timesheet")
    CommandLineRunner commandLineRunner(TimesheetDAO timesheetDAO) {
        return strings -> {
            timesheetDAO.deleteAll();
            List<Day> days = initializeDayList1("1","2020-12-26", 2020);
            List<Day> days2 = initializeDayList1("1","2020-12-19", 2020);
            List<Day> days3 = initializeDayList1("1","2020-12-12", 2020);
            List<Day> days4 = initializeDayList1("1","2020-12-5", 2020);
            List<Day> days5 = initializeDayList1("1","2020-11-28", 2020);
            List<Day> days6 = initializeDayList1("1","2020-11-21", 2020);

            //Initialize timesheet summary for user 1
            timesheetDAO.save(new Timesheet("1", "1", "2020-12-26", 32, 40, "Not Started", "N/A", "", days,5,2));
            timesheetDAO.save(new Timesheet("2", "1", "2020-12-19", 40, 40, "Incomplete", "Not approved", "", days2,2,3));
            timesheetDAO.save(new Timesheet("3", "1", "2020-12-12", 40, 40, "Not Started", "N/A", "", days3,6,6));
            timesheetDAO.save(new Timesheet("4", "1", "2020-12-5", 40, 40, "Completed", "Approved", "", days4,6,6));
            timesheetDAO.save(new Timesheet("5", "1", "2020-11-28", 40, 40, "Completed", "Approved", "", days5,6,6));
            timesheetDAO.save(new Timesheet("6", "1", "2020-11-21", 40, 40, "Completed", "Approved", "", days6,6,6));
        };
    }

    public List<Day> initializeDayList1(String userId, String weekEnding, Integer year) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date weekEndingDate = sdf.parse(weekEnding);
        Calendar ComparedDate = Calendar.getInstance();

        System.out.println("compareDate: "+ ComparedDate);


        holidaysDAO.deleteAll();

        Holidays holidaysTmp = new Holidays();
        List<String> s = Arrays.asList("2020-12-21","2020-12-25");
        holidaysTmp.setYear(2020);
        holidaysTmp.setHoliday(s);
        holidaysDAO.save(holidaysTmp);


        Holidays holidays = holidaysDAO.findByYear(year);

//        if(holidays == null) {
//            System.out.println("null");
//        }
        List<String> holidaysDates = new ArrayList<>();
        if(holidays != null) {
            holidaysDates = holidays.getHoliday();//Each date corresponds to the holiday

        }


        List<Day> dayList1 = templateDAO.findByUserId(userId).getDays();
        for(int i=0; i<dayList1.size(); i++) {
            if(i==0) {
                Calendar date = ComparedDate;
                date.setTime(weekEndingDate);
                date.add(Calendar.DAY_OF_YEAR, -6);

                String curDate = sdf.format(date.getTime());
                dayList1.get(i).setDate(curDate);

                if(holidaysDates.contains(curDate)) {
                    dayList1.get(i).setHoliday(true);
                    dayList1.get(i).setTotalHours(0.00);
                    dayList1.get(i).setStartTime("N/A");
                    dayList1.get(i).setEndTime("N/A");
                }
                else {
                    dayList1.get(i).setHoliday(false);
                }
                dayList1.get(i).setDay("Sunday");
            }
            else if(i==1) {
                Calendar date = ComparedDate;
                date.setTime(weekEndingDate);
                date.add(Calendar.DAY_OF_YEAR, -5);
                String curDate = sdf.format(date.getTime());
                dayList1.get(i).setDate(curDate);
                if(holidaysDates.contains(curDate)) {
                    dayList1.get(i).setHoliday(true);
                    dayList1.get(i).setTotalHours(0.00);
                    dayList1.get(i).setStartTime("N/A");
                    dayList1.get(i).setEndTime("N/A");
                }
                else {
                    dayList1.get(i).setHoliday(false);
                }
                dayList1.get(i).setDay("Monday");
            }
            else if(i==2) {
                Calendar date = ComparedDate;
                date.setTime(weekEndingDate);
                date.add(Calendar.DAY_OF_YEAR, -4);
                String curDate = sdf.format(date.getTime());
                dayList1.get(i).setDate(curDate);
                if(holidaysDates.contains(curDate)) {
                    dayList1.get(i).setHoliday(true);
                    dayList1.get(i).setTotalHours(0.00);
                    dayList1.get(i).setStartTime("N/A");
                    dayList1.get(i).setEndTime("N/A");
                }
                else {
                    dayList1.get(i).setHoliday(false);
                }
                dayList1.get(i).setDay("Tuesday");
            }
            else if(i==3) {
                Calendar date = ComparedDate;
                date.setTime(weekEndingDate);
                date.add(Calendar.DAY_OF_YEAR, -3);
                String curDate = sdf.format(date.getTime());
                dayList1.get(i).setDate(curDate);
                if(holidaysDates.contains(curDate)) {
                    dayList1.get(i).setHoliday(true);
                    dayList1.get(i).setTotalHours(0.00);
                    dayList1.get(i).setStartTime("N/A");
                    dayList1.get(i).setEndTime("N/A");
                }
                else {
                    dayList1.get(i).setHoliday(false);
                }
                dayList1.get(i).setDay("Wednesday");
            }
            else if(i==4) {
                Calendar date = ComparedDate;
                date.setTime(weekEndingDate);
                date.add(Calendar.DAY_OF_YEAR, -2);
                String curDate = sdf.format(date.getTime());
                dayList1.get(i).setDate(curDate);
                if(holidaysDates.contains(curDate)) {
                    dayList1.get(i).setHoliday(true);
                    dayList1.get(i).setTotalHours(0.00);
                    dayList1.get(i).setStartTime("N/A");
                    dayList1.get(i).setEndTime("N/A");
                }
                else {
                    dayList1.get(i).setHoliday(false);
                }
                dayList1.get(i).setDay("Thursday");
            }
            else if(i==5) {
                Calendar date = ComparedDate;
                date.setTime(weekEndingDate);
                date.add(Calendar.DAY_OF_YEAR, -1);
                String curDate = sdf.format(date.getTime());
                dayList1.get(i).setDate(curDate);
                if(holidaysDates.contains(curDate)) {
                    dayList1.get(i).setHoliday(true);
                    dayList1.get(i).setTotalHours(0.00);
                    dayList1.get(i).setStartTime("N/A");
                    dayList1.get(i).setEndTime("N/A");
                }
                else {
                    dayList1.get(i).setHoliday(false);
                }
                dayList1.get(i).setDay("Friday");
            }
            else{
                Calendar date = ComparedDate;
                date.setTime(weekEndingDate);
                date.add(Calendar.DAY_OF_YEAR, 0);
                String curDate = sdf.format(date.getTime());
                dayList1.get(i).setDate(curDate);
                if(holidaysDates.contains(curDate)) {
                    dayList1.get(i).setHoliday(true);
                    dayList1.get(i).setTotalHours(0.00);
                    dayList1.get(i).setStartTime("N/A");
                    dayList1.get(i).setEndTime("N/A");
                }
                else {
                    dayList1.get(i).setHoliday(false);
                }
                dayList1.get(i).setDay("Saturday");
            }
        }
        return dayList1;
    }


}
