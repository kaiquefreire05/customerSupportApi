package com.api.customersupport.domain.enums;

public enum ErrorCodeEnum {

    // Customer and Agents Error Codes
    ON0001("ON0001", "Email is invalid"),
    ON0002("ON0002", "Phone is invalid"),
    ON0003("ON0003", "An internal problem occurred while creating the customer"),
    ON0004("ON0004", "An internal problem occurred while deleting the customer"),
    ON0008("ON0008", "An internal problem occurred while deleting the agent"),
    ON0009("ON0009", "An internal problem occurred while creating the agent"),
    ON0005("ON0005", "Customer not found"),
    ON0006("ON0006", "Agent not found"),
    ON0007("ON0007", "This email is not available."),

    // Ticket Error Codes
    TS0001("TS0005", "An error occurred while creating the support ticket"),
    TS0002("TS0002", "Ticket Support not found"),

    // Feedbacks Error Codes
    FD0001("FD0001", "Rating must be between 1 and 5"),

    // Mapping Error Codes
    MP0001("MP0001", "An error occurred while mapping the entity to the domain model")

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
