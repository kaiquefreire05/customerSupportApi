package com.api.customersupport.domain.enums;

public enum ErrorCodeEnum {

    ON0001("ON0001", "Email is invalid"),
    ON0002("ON0002", "Phone is invalid"),

    ;

    // Attributes
    private String code;
    private String message;

    // Constructors
    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
