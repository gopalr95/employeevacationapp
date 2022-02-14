package com.example.employeevacationapp.controller;

import com.example.employeevacationapp.exception.EmployeeVacationException;
import com.example.employeevacationapp.exception.ErrorCode;
import com.example.employeevacationapp.request.EmployeeVacationRequest;
import com.example.employeevacationapp.service.EmployeeVacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gopal Reddy, Pagidimari
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class EmployeeVacationController {

    @Autowired
    EmployeeVacationService employeeVacationService;

    /**
     * The api updates the no of vacation days accumulated for an employee based on the no of work days provided
     *
     * @PathVariable noOfDaysWorked
     * @RequestBody EmployeeVacationRequest
     */
    @PutMapping(path = "/workdays/{noOfDaysWorked}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAvailableVacationDaysByWorkDays(@RequestBody EmployeeVacationRequest employeeVacationRequest, @PathVariable int noOfDaysWorked) throws EmployeeVacationException {
        String response = null;
        try {
            response = employeeVacationService.updateVacationDaysByWorkingDays(employeeVacationRequest, noOfDaysWorked);

        } catch (EmployeeVacationException ve) {
            if (ve.getCode().toString().equalsIgnoreCase(ErrorCode.NotFound.toString())) {
                return new ResponseEntity<>(ve.getMessage(), HttpStatus.NOT_FOUND);
            } else if (ve.getCode().toString().equalsIgnoreCase(ErrorCode.WorkdaysException.toString())){
                return new ResponseEntity<>(ve.getMessage(), HttpStatus.BAD_REQUEST);
            }else if (ve.getCode().toString().equalsIgnoreCase(ErrorCode.VacationDaysExceed.toString())) {
                return new ResponseEntity<>(ve.getMessage(), HttpStatus.BAD_REQUEST);
            } else if (ve.getCode().toString().equalsIgnoreCase(ErrorCode.BadRequest.toString())) {
                return new ResponseEntity<>(ve.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /**
     * The api updates the no of vacation days accumulated for an employee based on the no of vacation days used
     *
     * @PathVariable noOfVacationDaysUsed
     * @RequestBody EmployeeVacationRequest
     */
    @PutMapping(path = "/vacationdays/{noOfVacationDaysUsed}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAvailableVacationDaysByNoOfVacationDaysUsed(@RequestBody EmployeeVacationRequest employeeVacationRequest, @PathVariable int noOfVacationDaysUsed) throws EmployeeVacationException {
        String response = null;
        try {
            response = employeeVacationService.updateVacationDaysByVacationDaysTaken(employeeVacationRequest, noOfVacationDaysUsed);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EmployeeVacationException ve) {
            if (ve.getCode().toString().equalsIgnoreCase(ErrorCode.NotFound.toString())) {
                return new ResponseEntity<>(ve.getMessage(), HttpStatus.NOT_FOUND);
            } else if (ve.getCode().toString().equalsIgnoreCase(ErrorCode.VacationDaysExceed.toString())) {
                return new ResponseEntity<>(ve.getMessage(), HttpStatus.BAD_REQUEST);
            } else if (ve.getCode().toString().equalsIgnoreCase(ErrorCode.BadRequest.toString())) {
                return new ResponseEntity<>(ve.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
