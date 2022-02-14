package com.example.employeevacationapp.exception;

public class EmployeeVacationException extends RuntimeException{
    private ErrorCode code;
    private String message;

    public EmployeeVacationException() {
        super();
    }

    public EmployeeVacationException(String message) {
        super(message);
    }

    public EmployeeVacationException(ErrorCode code){
        super();
        this.code = code;

    }

    public EmployeeVacationException(ErrorCode code, String message){
        super();
        this.code = code;
        this.message = message;

    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
