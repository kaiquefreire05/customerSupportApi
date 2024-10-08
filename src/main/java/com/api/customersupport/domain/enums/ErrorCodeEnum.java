package com.api.customersupport.domain.enums;

public enum ErrorCodeEnum {

    // Customer and Agents Error Codes
    ON0001("ON0001", "Email is invalid"),
    ON0002("ON0002", "Phone is invalid"),
    ON0003("ON0003", "An internal problem occurred while creating the customer"),
    ON0004("ON0004", "An internal problem occurred while deleting the customer"),
    ON0005("ON0005", "Customer not found"),

    // Ticket Error Codes
    TS0001("TS0005", "An error occurred while creating the support ticket"),

    // Feedbacks Error Codes
    FD0001("FD0001", "Rating must be between 1 and 5"),

    ;

    // Attributes
    private String code;
    private String message;

    // Constructors
    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Method
    public static String concatError(String exceptionMessage, ErrorCodeEnum errorCodeEnum) {
        return String.format("%s: {%s}", errorCodeEnum.getMessage(), exceptionMessage);
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
