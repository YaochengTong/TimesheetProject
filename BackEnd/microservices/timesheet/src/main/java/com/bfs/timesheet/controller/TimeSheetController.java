package com.bfs.timesheet.controller;


import com.bfs.timesheet.domain.Holidays;
import com.bfs.timesheet.domain.PTO;
import com.bfs.timesheet.domain.Template;
import com.bfs.timesheet.domain.Timesheet;
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
@CrossOrigin
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
    @GetMapping("/weekEnding")
    public ResponseEntity<Timesheet> getOneTimeSheet(@RequestParam String userId, @RequestParam String weekEnding) {
        Timesheet timesheet = timesheetDAO.findByUserIdAndWeekEnding(userId, weekEnding);
        return new ResponseEntity(timesheet,HttpStatus.OK);
    }


    //save new TimeSheet
    @PostMapping("/add")
    public ResponseEntity<String> addTimeSheet(@RequestBody Timesheet timesheet) {
        timesheetDAO.save(timesheet);
        return ResponseEntity.ok("Successfully Add TimeSheet");
    }

    //update TimeSheet
    @PutMapping("/update")
    public ResponseEntity<String> updateTimeSheet(@RequestBody Timesheet timesheet) {
        Timesheet updatedTimeSheet = timesheetDAO.findByUserIdAndWeekEnding(timesheet.getUserId(),timesheet.getWeekEnding());
        updatedTimeSheet.setTotalBillingHour(timesheet.getTotalBillingHour());
        updatedTimeSheet.setTotalCompensatedHour(timesheet.getTotalCompensatedHour());
        updatedTimeSheet.setSubmissionStatus(timesheet.getSubmissionStatus());
        updatedTimeSheet.setApprovalStatus(timesheet.getApprovalStatus());
        updatedTimeSheet.setComment(timesheet.getComment());
        updatedTimeSheet.setDays(timesheet.getDays());
        updatedTimeSheet.setFloatingLeft(timesheet.getFloatingLeft());
        updatedTimeSheet.setHolidayLeft(timesheet.getHolidayLeft());
        timesheetDAO.save(updatedTimeSheet);

        return ResponseEntity.ok("Successfully Update TimeSheet");
    }

    @PutMapping("/updateDefault") //for set default
    public ResponseEntity<String> updateTemplate(@RequestBody Template template){
        Template original = templateDAO.findByUserId(template.getUserId());
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
    public void updatePTO(@RequestBody PTO pto){
        PTO original = ptodao.findByUserIdAndYear(pto.getUserId(), pto.getYear());
        if(original != null){
            original.setFloatingCount(pto.getFloatingCount());
            original.setVacationCount(pto.getVacationCount());
            ptodao.save(original);
        }else{
            System.out.println("No PTO found");
        }
    }

    @PostMapping("/updateFloating")
    public ResponseEntity<String> updateFloating(@RequestBody PTO pto){
        PTO checkPTO = ptodao.findByUserIdAndYear(pto.getUserId(), pto.getYear());
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
            //Create a new PTO floating day document for that year
//            Random random = new Random();
//            int id = random.nextInt(100);
//            pto.setId(id);
//            pto.setUserId(pto.getUserId());
//            pto.setFloatingCount(2); //use one floating day
//            pto.setVacationCount(3);
//            return new ResponseEntity("Take one day off your floating day ",HttpStatus.OK);

        }
    }

    @PostMapping("/updateVacation")
    public ResponseEntity<String> updateVacation(@RequestBody PTO pto){
        PTO checkVacation = ptodao.findByUserIdAndYear(pto.getUserId(), pto.getYear());
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
//            System.out.println("No PTO vacation found");
//            Random random = new Random();
//            int id = random.nextInt(100);
//            pto.setId(id);
//            pto.setUserId(pto.getUserId());
//            pto.setFloatingCount(3);
//            pto.setVacationCount(2);//use one vacation day
            return new ResponseEntity("You have no PTO vacation day",HttpStatus.BAD_REQUEST);

        }

    }

}
