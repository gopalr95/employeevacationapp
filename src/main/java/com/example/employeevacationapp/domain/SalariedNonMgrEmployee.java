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
public class SalariedNonMgrEmployee extends Employee {

    private static List<SalariedNonMgrEmployee> listOfSalariedNonManagers;
    private static final int MAX_NON_MGR_EMPLOYEE_VACATION_DAYS = 15;

    /**Instantiating the 10 salaried Non-manager employee instances*/
    static {
        listOfSalariedNonManagers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {

            SalariedNonMgrEmployee salariedNonMgrEmployee = new SalariedNonMgrEmployee(String.valueOf(10 + i), EmployeeType.SALARIED_NON_MGR, 0, 0);
            listOfSalariedNonManagers.add(salariedNonMgrEmployee);
        }
    }

    public SalariedNonMgrEmployee() {
        super();
    }

    public SalariedNonMgrEmployee(String employeeId, String employeeType, int noOfDaysWorked, int noOfVacationDaysAccumulated) {
        super(employeeId, employeeType, noOfDaysWorked, noOfVacationDaysAccumulated);
    }

    /**
     * This method updates the no of vacation days accumulated for a salaried Non-manager employee, if the employeeId is found
     *
     * @param employeeVacationRequest
     * @param noOfDaysWorked
     * @return a success message with no of vacation days accumulated
     * @throws EmployeeVacationException
     */
    @Override
    public String work(EmployeeVacationRequest employeeVacationRequest, int noOfDaysWorked) {
        if (getNoOfWorkingDays() - noOfDaysWorked > MAX_NON_MGR_EMPLOYEE_VACATION_DAYS){
            throw new EmployeeVacationException(ErrorCode.VacationDaysExceed, ErrorMessages.VACATION_DAYS_EXCEEDED);
        }
        for (SalariedNonMgrEmployee e : getListOfSalariedNonManagers()) {
            if (e.getEmployeeId().equalsIgnoreCase(employeeVacationRequest.getEmployeeId())) {
                if (e.getNoOfDaysWorked() <= noOfDaysWorked) {
                    e.setNoOfDaysWorked(noOfDaysWorked);
                    e.setNoOfVacationDaysAccumulated(noOfDaysWorked-(getNoOfWorkingDays() - MAX_NON_MGR_EMPLOYEE_VACATION_DAYS));

                    return SuccessMessage.SALARIED_NON_MGR_SUCCESS_MESSAGE + e.getNoOfVacationDaysAccumulated();
                }
                else {
                    throw new EmployeeVacationException(ErrorCode.WorkdaysException, ErrorMessages.WORKING_DAYS_EXCEPTION_MESSAGE);
                }
            }
        }
        throw new EmployeeVacationException(ErrorCode.NotFound, ErrorMessages.EMPLOYEE_NOT_FOUND);
    }

    /**
     * This method updates the no of vacation days accumulated for a salaried Non-manager employee, if the employeeId is found
     *
     * @param employeeVacationRequest
     * @param noOfVacationDaysUsed
     * @return a success message with no of vacation days accumulated
     * @throws EmployeeVacationException
     */
    @Override
    public String takeVacation(EmployeeVacationRequest employeeVacationRequest, int noOfVacationDaysUsed) {
        for (SalariedNonMgrEmployee e : getListOfSalariedNonManagers()) {
            if (e.getEmployeeId().equalsIgnoreCase(employeeVacationRequest.getEmployeeId()) && e.getEmployeeType().equalsIgnoreCase(employeeVacationRequest.getEmployeeType())) {
                if (MAX_NON_MGR_EMPLOYEE_VACATION_DAYS - e.getNoOfVacationDaysAccumulated() >= noOfVacationDaysUsed) {
                    e.setNoOfVacationDaysAccumulated(e.getNoOfVacationDaysAccumulated() + noOfVacationDaysUsed);
                    return SuccessMessage.SALARIED_NON_MGR_SUCCESS_MESSAGE + e.getNoOfVacationDaysAccumulated();
                } else {
                    throw new EmployeeVacationException(ErrorCode.VacationDaysExceed, ErrorMessages.VACATION_DAYS_EXCEEDED);
                }


            }
        }

        throw new EmployeeVacationException(ErrorCode.NotFound, ErrorMessages.EMPLOYEE_NOT_FOUND);
    }

    public List<SalariedNonMgrEmployee> getListOfSalariedNonManagers() {
        return listOfSalariedNonManagers;
    }

    @Override
    public String toString() {
        return "SalariedNonMgrEmployee{} " + super.toString();
    }
}
