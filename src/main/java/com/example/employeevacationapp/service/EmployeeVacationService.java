package com.example.employeevacationapp.service;


import com.example.employeevacationapp.constants.EmployeeType;
import com.example.employeevacationapp.domain.HourlyEmployee;
import com.example.employeevacationapp.domain.SalariedMgrEmployee;
import com.example.employeevacationapp.domain.SalariedNonMgrEmployee;
import com.example.employeevacationapp.exception.EmployeeVacationException;
import com.example.employeevacationapp.exception.ErrorCode;
import com.example.employeevacationapp.exception.ErrorMessages;
import com.example.employeevacationapp.request.EmployeeVacationRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Gopal Reddy, Pagidimari
 * @version 1.0
 */
@Service
public class EmployeeVacationService {

    /**
     * This method updates the no of vacation days accumulated for an employee based on the no of work days provided
     *
     * @Param employeeVacationRequest
     * @Param noOfDaysWorked
     * @return returns a success messages with no of vacation days accumulated
     * @throws EmployeeVacationException
     */
    public String updateVacationDaysByWorkingDays(EmployeeVacationRequest employeeVacationRequest, int noOfDaysWorked) {
        validateEmployeeVacationRequest(employeeVacationRequest);
        validateNoOfWorkingDays(noOfDaysWorked);
        String employeeType = employeeVacationRequest.getEmployeeType();

        switch (employeeType) {
            case EmployeeType.HOURLY:
                return new HourlyEmployee().work(employeeVacationRequest, noOfDaysWorked);
            case EmployeeType.SALARIED_NON_MGR:
                return new SalariedNonMgrEmployee().work(employeeVacationRequest, noOfDaysWorked);
            case EmployeeType.SALARIED_MGR:
                return new SalariedMgrEmployee().work(employeeVacationRequest, noOfDaysWorked);
            default:
                throw new EmployeeVacationException(ErrorCode.NotFound, ErrorMessages.EMPLOYEE_TYPE_NOT_FOUND);
        }

    }

    /**
     * This method updates the no of vacation days accumulated for an employee based on the no of vacation days used
     *
     * @Param employeeVacationRequest
     * @Param noOfVacationDaysUsed
     * @return returns a success messages with no of vacation days accumulated
     * @throws EmployeeVacationException
     *
     */
    public String updateVacationDaysByVacationDaysTaken(EmployeeVacationRequest employeeVacationRequest, int noOfVacationDaysUsed) {
        validateEmployeeVacationRequest(employeeVacationRequest);
        validateNoOfVacationDaysUsed(noOfVacationDaysUsed);
        String employeeType = employeeVacationRequest.getEmployeeType().toUpperCase();

        switch (employeeType) {
            case EmployeeType.HOURLY:
                return new HourlyEmployee().takeVacation(employeeVacationRequest, noOfVacationDaysUsed);
            case EmployeeType.SALARIED_NON_MGR:
                return new SalariedNonMgrEmployee().takeVacation(employeeVacationRequest, noOfVacationDaysUsed);
            case EmployeeType.SALARIED_MGR:
                return new SalariedMgrEmployee().takeVacation(employeeVacationRequest, noOfVacationDaysUsed);
            default:
                throw new EmployeeVacationException(ErrorCode.NotFound, ErrorMessages.EMPLOYEE_TYPE_NOT_FOUND);
        }

    }

    /**
     * This method validates the EmployeeVacationRequest
     *
     * @param employeeVacationRequest
     * @throws EmployeeVacationException
     */
    private void validateEmployeeVacationRequest(EmployeeVacationRequest employeeVacationRequest) {
        if (employeeVacationRequest == null) {
            throw new EmployeeVacationException(ErrorCode.BadRequest, ErrorMessages.REQUEST_MISSING);
        } else if (StringUtils.isBlank(employeeVacationRequest.getEmployeeType())) {
            throw new EmployeeVacationException(ErrorCode.BadRequest, ErrorMessages.EMPLOYEE_TYPE_IS_MISSING);
        } else if (StringUtils.isBlank(employeeVacationRequest.getEmployeeId())) {
            throw new EmployeeVacationException(ErrorCode.BadRequest, ErrorMessages.EMPLOYEE_ID_IS_MISSING);
        }
    }

    /**
     * This method validates the noOfDaysWorked
     *
     * @param noOfDaysWorked
     * @throws EmployeeVacationException
     */
    private void validateNoOfWorkingDays(int noOfDaysWorked) {
        if (noOfDaysWorked >= 260 || noOfDaysWorked <=0)  {
            throw new EmployeeVacationException(ErrorCode.BadRequest, ErrorMessages.WORKING_DAYS_INVALID_RANGE);
        }
    }

    /**
     * This method validates the noOfVacationDaysUsed
     *
     * @param noOfVacationDaysUsed
     * @throws EmployeeVacationException
     */
    private void validateNoOfVacationDaysUsed(int noOfVacationDaysUsed) {
        if (noOfVacationDaysUsed < 0) {
            throw new EmployeeVacationException(ErrorCode.BadRequest, ErrorMessages.VACATION_DAYS_LESS_THAN_0);
        }
    }
}
