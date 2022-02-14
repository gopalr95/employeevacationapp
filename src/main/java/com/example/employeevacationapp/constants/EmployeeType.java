package com.example.employeevacationapp.constants;

public final class EmployeeType {
    public static final String HOURLY = "HOURLY";
    public static final String SALARIED_NON_MGR = "SALARIED_NON_MGR";
    public static final String SALARIED_MGR = "SALARIED_MGR";

    private EmployeeType(){
        throw new IllegalStateException("EmployeeType is a Utility class");
    }

}
