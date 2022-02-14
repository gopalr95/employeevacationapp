package com.example.employeevacationapp.domain;

import com.example.employeevacationapp.request.EmployeeVacationRequest;

/**
 * @author Gopal Reddy, Pagidimari
 * @version 1.0
 */
public abstract class Employee {
    private String employeeId;
    private String employeeType;
    private int noOfDaysWorked;
    private int noOfVacationDaysAccumulated;
    private static final int NO_OF_WORKING_DAYS = 260;

    protected Employee() {
    }

    protected Employee(String employeeId, String employeeType, int noOfDaysWorked, int noOfVacationDaysAccumulated) {
        this.employeeId = employeeId;
        this.employeeType = employeeType;
        this.noOfDaysWorked = noOfDaysWorked;
        this.noOfVacationDaysAccumulated = noOfVacationDaysAccumulated;
    }

    public abstract String work(EmployeeVacationRequest employeeVacationRequest, int noOfDaysWorked);

    public abstract String takeVacation(EmployeeVacationRequest employeeVacationRequest, int noOfVacationDaysRequested);

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public int getNoOfDaysWorked() {
        return noOfDaysWorked;
    }

    public void setNoOfDaysWorked(int noOfDaysWorked) {
        this.noOfDaysWorked = noOfDaysWorked;
    }

    public int getNoOfVacationDaysAccumulated() {
        return noOfVacationDaysAccumulated;
    }

    public void setNoOfVacationDaysAccumulated(int noOfVacationDaysAccumulated) {
        this.noOfVacationDaysAccumulated = noOfVacationDaysAccumulated;
    }

    public int getNoOfWorkingDays() {
        return NO_OF_WORKING_DAYS;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId='" + employeeId + '\'' + ", employeeType='" + employeeType + '\'' + ", noOfDaysWorked=" + noOfDaysWorked + ", noOfVacationDaysAccumulated=" + noOfVacationDaysAccumulated + ", noOfWorkingDays=" + NO_OF_WORKING_DAYS + '}';
    }
}
