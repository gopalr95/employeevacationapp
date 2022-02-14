package com.example.employeevacationapp.domain;

import com.example.employeevacationapp.constants.EmployeeType;
import com.example.employeevacationapp.constants.SuccessMessage;
import com.example.employeevacationapp.exception.EmployeeVacationException;
import com.example.employeevacationapp.exception.ErrorCode;
import com.example.employeevacationapp.exception.ErrorMessages;
import com.example.employeevacationapp.request.EmployeeVacationRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gopal Reddy, Pagidimari
 * @version 1.0
 */
@Component
public class HourlyEmployee extends Employee {

    private static List<HourlyEmployee> listOfHourlyEmployees;
    private static final int MAX_HOURLY_EMPLOYEE_VACATION_DAYS = 10;

    public HourlyEmployee() {
        super();
    }

    /**Instantiating the 10 hourly employee instances*/
    static {
        listOfHourlyEmployees = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            HourlyEmployee hourlyEmployee = new HourlyEmployee(String.valueOf(i), EmployeeType.HOURLY, 0, 0);
            listOfHourlyEmployees.add(hourlyEmployee);

        }
    }

    public HourlyEmployee(String employeeId, String employeeType, int noOfDaysWorked, int noOfVacationDaysAccumulated) {
        super(employeeId, employeeType, noOfDaysWorked, noOfVacationDaysAccumulated);
    }


    /**
     * This method updates the no of vacation days accumulated for an hourly employee, if the employeeId is found
     *
     * @param employeeVacationRequest
     * @param noOfDaysWorked
     * @return a success message with no of vacation days accumulated
     * @throws EmployeeVacationException
     */
    @Override
    public String work(EmployeeVacationRequest employeeVacationRequest, int noOfDaysWorked) {
        if (getNoOfWorkingDays() - noOfDaysWorked > MAX_HOURLY_EMPLOYEE_VACATION_DAYS){
            throw new EmployeeVacationException(ErrorCode.VacationDaysExceed, ErrorMessages.VACATION_DAYS_EXCEEDED);
        }
        for (HourlyEmployee e : getListOfHourlyEmployees()) {
            if (e.getEmployeeId().equalsIgnoreCase(employeeVacationRequest.getEmployeeId())) {
                if (e.getNoOfDaysWorked() <= noOfDaysWorked) {
                    e.setNoOfDaysWorked(noOfDaysWorked);
                    e.setNoOfVacationDaysAccumulated(noOfDaysWorked-(getNoOfWorkingDays() - MAX_HOURLY_EMPLOYEE_VACATION_DAYS));

                    return SuccessMessage.HOURLY_SUCCESS_MESSAGE + e.getNoOfVacationDaysAccumulated();
                } else {
                    throw new EmployeeVacationException(ErrorCode.WorkdaysException, ErrorMessages.WORKING_DAYS_EXCEPTION_MESSAGE);
                }
            }
        }

        throw new EmployeeVacationException(ErrorCode.NotFound, ErrorMessages.EMPLOYEE_NOT_FOUND);
    }

    /**
     * This method updates the no of vacation days accumulated for an hourly employee, if the employeeId is found
     *
     * @param employeeVacationRequest
     * @param noOfVacationDaysUsed
     * @return a success message with no of vacation days accumulated
     * @throws EmployeeVacationException
     */
    @Override
    public String takeVacation(EmployeeVacationRequest employeeVacationRequest, int noOfVacationDaysUsed) {
        for (HourlyEmployee e : getListOfHourlyEmployees()) {
            if (e.getEmployeeId().equalsIgnoreCase(employeeVacationRequest.getEmployeeId())) {
                if (MAX_HOURLY_EMPLOYEE_VACATION_DAYS - e.getNoOfVacationDaysAccumulated() >= noOfVacationDaysUsed) {
                    e.setNoOfVacationDaysAccumulated(e.getNoOfVacationDaysAccumulated() + noOfVacationDaysUsed);
                    return SuccessMessage.HOURLY_SUCCESS_MESSAGE + e.getNoOfVacationDaysAccumulated();
                } else {
                    throw new EmployeeVacationException(ErrorCode.VacationDaysExceed, ErrorMessages.VACATION_DAYS_EXCEEDED);
                }
            }
        }

        throw new EmployeeVacationException(ErrorCode.NotFound, ErrorMessages.EMPLOYEE_NOT_FOUND);
    }

    public List<HourlyEmployee> getListOfHourlyEmployees() {
        return listOfHourlyEmployees;
    }

    @Override
    public String toString() {
        return "HourlyEmployee{} " + super.toString();
    }
}
