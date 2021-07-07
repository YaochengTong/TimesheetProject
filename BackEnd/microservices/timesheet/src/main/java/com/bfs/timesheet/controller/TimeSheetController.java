package com.bfs.timesheet.controller;


import com.bfs.timesheet.domain.*;
import com.bfs.timesheet.repository.HolidaysDAO;
import com.bfs.timesheet.repository.PTODAO;
import com.bfs.timesheet.repository.TemplateDAO;
import com.bfs.timesheet.repository.TimesheetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
//@CrossOrigin
@RequestMapping("/timeSheet")
public class TimeSheetController {

    @Autowired
    private TimesheetDAO timesheetDAO;

    @Autowired
    private HolidaysDAO holidaysDAO;

    @Autowired
    private PTODAO ptodao;


    @Autowired
    private TemplateDAO templateDAO;

    private Logger logger = LoggerFactory.getLogger(getClass());


    //for section A - summary
    @GetMapping("/summary")
    public ResponseEntity<List<Timesheet>> getListOfTimesheet(@RequestParam String userId) {
        List<Timesheet> list = timesheetDAO.findAllByUserId(userId);

        if (list == null) {
            return new ResponseEntity("No Any TimeSheet", HttpStatus.NOT_FOUND);
        }
        logger.debug(list.toString());
        return new ResponseEntity(list,HttpStatus.OK);
    }

    //a default ‘Week Ending’ of the current ending week
    @GetMapping("/weekEnding/{weekEnding}")
    public ResponseEntity<Timesheet> getOneTimeSheet(@RequestParam String userId, @PathVariable String weekEnding) {
        Timesheet timesheet = timesheetDAO.findByUserIdAndWeekEnding(userId, weekEnding);
        return new ResponseEntity(timesheet,HttpStatus.OK);
    }


    //save new TimeSheet
    @PostMapping("/add/{userId}")
    public ResponseEntity<String> addTimeSheet(@RequestParam String userId, @RequestBody Timesheet timesheet) {
        List<Timesheet> list = timesheetDAO.findAll();
        int size = list.size();
        timesheet.setId(String.valueOf(++size));
        timesheet.setId(userId);
        timesheetDAO.save(timesheet);
        return ResponseEntity.ok("Successfully Add TimeSheet");
    }

    //update TimeSheet
    @PutMapping("/update")
    public ResponseEntity<String> updateTimeSheet(@RequestParam String userId, @RequestBody Timesheet timesheet) {
        Timesheet updatedTimeSheet = timesheetDAO.findByUserIdAndWeekEnding(userId,timesheet.getWeekEnding());

        timesheetDAO.delete(updatedTimeSheet);

        List<Day> list = updatedTimeSheet.getDays();

//        Double totalHours;
        Double totalAllBillingHour = 0.0;

        Double totalAllCompensatedHour = 0.0;

        Integer floatingAllLeft = updatedTimeSheet.getFloatingLeft();
        Integer holidayAllLeft = updatedTimeSheet.getHolidayLeft();

        Integer floatingCount = 0;
        Integer holidayCount = 0;
        Integer vacationCount = 0;

        for(Day day : list)
        {
            double totalHours = Integer.valueOf(day.getEndTime()) - Integer.valueOf(day.getStartTime());
            totalAllBillingHour += totalHours;
            if(day.getFloating().equals("true"))
                floatingCount++;
            if(day.getHoliday().equals("true"))
                holidayCount++;
            if(day.getVacation().equals("true"))
                vacationCount++;
        }
        floatingAllLeft -= floatingCount;
        holidayAllLeft -= holidayCount;

        totalAllCompensatedHour = totalAllBillingHour + (floatingCount + holidayCount + vacationCount)*8;

        updatedTimeSheet.setTotalBillingHour(totalAllBillingHour);
        updatedTimeSheet.setTotalCompensatedHour(totalAllCompensatedHour);
        updatedTimeSheet.setFloatingLeft(floatingAllLeft);
        updatedTimeSheet.setHolidayLeft(holidayAllLeft);


        updatedTimeSheet.setSubmissionStatus(timesheet.getSubmissionStatus());
        updatedTimeSheet.setApprovalStatus(timesheet.getApprovalStatus());
        updatedTimeSheet.setComment(timesheet.getComment());
        updatedTimeSheet.setDays(timesheet.getDays());

        timesheetDAO.save(updatedTimeSheet);

        return ResponseEntity.ok("Successfully Update TimeSheet");
    }

    @PutMapping("/updateDefault") //for set default
    public ResponseEntity<String> updateTemplate(@RequestParam String userId,@RequestBody Template template){
        Template original = templateDAO.findByUserId(userId);
        templateDAO.delete(original);
        original.setDays(template.getDays());
        templateDAO.save(original);
        return ResponseEntity.ok("Successfully Update time sheet");
    }

    @GetMapping("/holiday")
    public ResponseEntity<Holidays> getHolidays() {
        Holidays holidays = holidaysDAO.findByYear(2021);
        if (holidays == null) {
            return new ResponseEntity("No Holidays",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(holidays,HttpStatus.OK);
    }


    @GetMapping("/pto")
    public ResponseEntity<PTO> findByUserIdAndYear(@RequestParam String userId, @RequestParam Integer year){
        PTO pto = ptodao.findByUserIdAndYear(userId, year);
        if (pto == null) {
            return new ResponseEntity("No PTO",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(pto,HttpStatus.OK);
    }



    @PutMapping("/updatePto")
    public void updatePTO(@RequestParam String userId,@RequestBody PTO pto){
        PTO original = ptodao.findByUserIdAndYear(userId, pto.getYear());
        if(original != null){
            ptodao.delete(original);
            original.setFloatingCount(pto.getFloatingCount());
            original.setVacationCount(pto.getVacationCount());
            ptodao.save(original);
        }else{
            System.out.println("No PTO found");
        }
    }

    @PostMapping("/updateFloating")
    public ResponseEntity<String> updateFloating(@RequestParam String userId,@RequestBody PTO pto){
        PTO checkPTO = ptodao.findByUserIdAndYear(userId, pto.getYear());
        if(checkPTO != null){
            if(checkPTO.getFloatingCount() > 0){
                checkPTO.setFloatingCount(checkPTO.getFloatingCount()-1);
                ptodao.delete(pto); //remove the old document
                ptodao.save(checkPTO); //save the new document
                //Update the floating day left on Summary comment
            }else{
                return new ResponseEntity("No more floating day",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Take one day off your floating day ",HttpStatus.OK);

        }else{
            return new ResponseEntity("You have no PTO",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateVacation")
    public ResponseEntity<String> updateVacation(@RequestParam String userId, @RequestBody PTO pto){
        PTO checkVacation = ptodao.findByUserIdAndYear(userId, pto.getYear());
        if(checkVacation != null){
            if(checkVacation.getVacationCount() > 0){
                checkVacation.setVacationCount(checkVacation.getVacationCount()- 1);
                ptodao.delete(pto);

                checkVacation.setFloatingCount(checkVacation.getFloatingCount()-1);

                ptodao.save(checkVacation);
            }
            else{
                return new ResponseEntity("No more vacation day left",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Take one day off your vacation day ",HttpStatus.OK);

        }else{
            return new ResponseEntity("You have no PTO vacation day",HttpStatus.BAD_REQUEST);

        }

    }

    @PatchMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam String userId, @RequestParam String weekEnding, @RequestParam String approvalStatus){
        Timesheet timesheet = timesheetDAO.findByUserIdAndWeekEnding(userId, weekEnding);
        timesheetDAO.delete(timesheet);
        timesheet.setApprovalStatus(approvalStatus);
        timesheetDAO.save(timesheet);
        return new ResponseEntity<>("Successfully change Approval Status", HttpStatus.OK);

    }

}
