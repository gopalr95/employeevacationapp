package com.example.employeevacationapp.request;

public class EmployeeVacationRequest {
    private String employeeId;
    private String employeeType;

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

    @Override
    public String toString() {
        return "EmployeeVacationRequest{" +
                "employeeId=" + employeeId +
                ", employeeType='" + employeeType + '\'' +
                '}';
    }
}
