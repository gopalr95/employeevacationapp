package com.example.employeevacationapp.exception;

public class ErrorMessages {
    public static final String EMPLOYEE_TYPE_NOT_FOUND = "unknown employee type";
    public static final String REQUEST_MISSING = "request can't be null";
    public static final String EMPLOYEE_TYPE_IS_MISSING = "employeeType can't be null or blank";
    public static final String EMPLOYEE_ID_IS_MISSING = "employeeId can't be null or blank";
    public static final String WORKING_DAYS_INVALID_RANGE = "no of working days should be between 0 and 260";
    public static final String VACATION_DAYS_LESS_THAN_0 = "vacation days requested can't be negative";
    public static final String EMPLOYEE_NOT_FOUND = "employee not found";
    public static final String VACATION_DAYS_EXCEEDED = "vacation days exceed";
    public static final String WORKING_DAYS_EXCEPTION_MESSAGE = "working days entered can't be less than accumulated work days";

    private ErrorMessages(){
        throw new IllegalStateException("ErrorMessages is a Utility class");
    }
}

