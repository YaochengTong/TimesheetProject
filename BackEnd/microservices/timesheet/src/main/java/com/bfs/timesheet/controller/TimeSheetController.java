package com.bfs.timesheet.controller;

import com.bfs.timesheet.domain.Day;
import com.bfs.timesheet.domain.Holidays;
import com.bfs.timesheet.domain.PTO;
import com.bfs.timesheet.domain.Template;
import com.bfs.timesheet.domain.Timesheet;
import com.bfs.timesheet.repository.HolidaysDAO;
import com.bfs.timesheet.repository.PTODAO;
import com.bfs.timesheet.repository.TemplateDAO;
import com.bfs.timesheet.repository.TimesheetDAO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //a default ‘Week Ending’ of the current ending week
    @GetMapping("/{weekEnding}")
    public ResponseEntity<Timesheet> getOneTimeSheet(@RequestParam String userId, @PathVariable String weekEnding) {
        Timesheet timesheet = timesheetDAO.findByUserIdAndWeekEnding(userId, weekEnding);
        return new ResponseEntity(timesheet, HttpStatus.OK);
    }

    //save new TimeSheet
    @PostMapping("/add")
    public ResponseEntity<String> addTimeSheet(@RequestBody Timesheet timesheet) {
        //Timesheet updatedTimeSheet = calculateTimesheet(timesheet);
        //System.out.println(updatedTimeSheet);
        timesheetDAO.save(timesheet);
        return ResponseEntity.ok("Successfully Add TimeSheet");
    }

    public Timesheet calculateTimesheet(Timesheet timesheet) {
        List<Day> list = timesheet.getDays();
        if (list == null) { return timesheet; }
        System.out.println(list);
        double totalAllBillingHour = 0.0;
        double totalAllCompensatedHour = 0.0;

        int floatingCount = 0;
        int holidayCount = 0;
        int vacationCount = 0;

        for (Day day : list) {
            if (day.getStartTime().equals("N/A") || day.getEndTime().equals("N/A")) {
                day.setTotalHours((double) 0);
                continue;
            }
            String[] endTimeSplit = day.getEndTime().split(":");
            String[] startTimeSplit = day.getStartTime().split(":");
            double totalHours = Integer.parseInt(endTimeSplit[0]) - Integer.parseInt(startTimeSplit[0]);
            totalAllBillingHour += totalHours;
            if (day.getFloating()) { floatingCount++; }
            if (day.getHoliday()) { holidayCount++; }
            if (day.getVacation()) { vacationCount++; }
            day.setTotalHours(totalHours);
            System.out.println(day);
            System.out.println(floatingCount + " " + holidayCount + " " + vacationCount);
        }

        totalAllCompensatedHour = totalAllBillingHour + (floatingCount + holidayCount + vacationCount) * 8;

        timesheet.setTotalBillingHour(totalAllBillingHour);
        timesheet.setTotalCompensatedHour(totalAllCompensatedHour);
        timesheet.setFloatingLeft(6 - floatingCount);
        timesheet.setHolidayLeft(6 - holidayCount);

        timesheet.setSubmissionStatus(timesheet.getSubmissionStatus());
        timesheet.setApprovalStatus(timesheet.getApprovalStatus());
        timesheet.setComment(timesheet.getComment());
        timesheet.setDays(list);
        return timesheet;
    }

    //update TimeSheet
    @PutMapping("/update")
    public ResponseEntity<String> updateTimeSheet(@RequestBody Timesheet timesheet) {
        Timesheet updatedTimeSheet = timesheetDAO
            .findByUserIdAndWeekEnding(timesheet.getUserId(), timesheet.getWeekEnding());
        timesheetDAO.delete(updatedTimeSheet);
        updatedTimeSheet = calculateTimesheet(timesheet);
        timesheetDAO.save(updatedTimeSheet);
        return ResponseEntity.ok("Successfully Update TimeSheet");
    }

    @PutMapping("/updateApprovalStatus")
    public ResponseEntity<String> updateTimeSheetApprovalStatus(@RequestBody Timesheet timesheet) {
        Timesheet updatedTimeSheet = timesheetDAO
            .findByUserIdAndWeekEnding(timesheet.getUserId(), timesheet.getWeekEnding());
        timesheetDAO.delete(updatedTimeSheet);
        updatedTimeSheet.setApprovalStatus(timesheet.getApprovalStatus());
        timesheetDAO.save(updatedTimeSheet);
        return ResponseEntity.ok("Successfully Update TimeSheet");
    }

    @PutMapping("/updateDefault") //for set default
    public ResponseEntity<String> updateTemplate(@RequestParam String userId, @RequestBody Template template) {
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
            return new ResponseEntity("No Holidays", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(holidays, HttpStatus.OK);
    }

    @GetMapping("/pto")
    public ResponseEntity<PTO> findByUserIdAndYear(@RequestParam String userId, @RequestParam Integer year) {
        PTO pto = ptodao.findByUserIdAndYear(userId, year);
        if (pto == null) {
            return new ResponseEntity("No PTO", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(pto, HttpStatus.OK);
    }

    @PutMapping("/updatePto")
    public void updatePTO(@RequestParam String userId, @RequestBody PTO pto) {
        PTO original = ptodao.findByUserIdAndYear(userId, pto.getYear());
        if (original != null) {
            ptodao.delete(original);
            original.setFloatingCount(pto.getFloatingCount());
            original.setVacationCount(pto.getVacationCount());
            ptodao.save(original);
        } else {
            System.out.println("No PTO found");
        }
    }

    @PostMapping("/updateFloating")
    public ResponseEntity<String> updateFloating(@RequestParam String userId, @RequestBody PTO pto) {
        PTO checkPTO = ptodao.findByUserIdAndYear(userId, pto.getYear());
        if (checkPTO != null) {
            if (checkPTO.getFloatingCount() > 0) {
                checkPTO.setFloatingCount(checkPTO.getFloatingCount() - 1);
                ptodao.delete(pto); //remove the old document
                ptodao.save(checkPTO); //save the new document
                //Update the floating day left on Summary comment
            } else {
                return new ResponseEntity("No more floating day", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Take one day off your floating day ", HttpStatus.OK);
        } else {
            return new ResponseEntity("You have no PTO", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateVacation")
    public ResponseEntity<String> updateVacation(@RequestParam String userId, @RequestBody PTO pto) {
        PTO checkVacation = ptodao.findByUserIdAndYear(userId, pto.getYear());
        if (checkVacation != null) {
            if (checkVacation.getVacationCount() > 0) {
                checkVacation.setVacationCount(checkVacation.getVacationCount() - 1);
                ptodao.delete(pto);

                checkVacation.setFloatingCount(checkVacation.getFloatingCount() - 1);

                ptodao.save(checkVacation);
            } else {
                return new ResponseEntity("No more vacation day left", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Take one day off your vacation day ", HttpStatus.OK);
        } else {
            return new ResponseEntity("You have no PTO vacation day", HttpStatus.BAD_REQUEST);
        }
    }

    //HR
    @PatchMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam String userId, @RequestParam String weekEnding,
        @RequestParam String approvalStatus) {
        Timesheet timesheet = timesheetDAO.findByUserIdAndWeekEnding(userId, weekEnding);
        timesheetDAO.delete(timesheet);
        timesheet.setApprovalStatus(approvalStatus);
        timesheetDAO.save(timesheet);
        return new ResponseEntity<>("Successfully change Approval Status", HttpStatus.OK);
    }

}
