package com.example.employeevacationapp.constants;

public class SuccessMessage {
    public static final String HOURLY_SUCCESS_MESSAGE = "hourly employee no of vacation days accumulated is updated to: ";
    public static final String SALARIED_NON_MGR_SUCCESS_MESSAGE = "salaried non mgr no of vacation days accumulated is updated to: ";
    public static final String SALARIED_MGR_SUCCESS_MESSAGE = "salaried mgr no of vacation days accumulated is updated to: ";

    private SuccessMessage(){
        throw new IllegalStateException("SuccessMessage is a Utility class");
    }
}
