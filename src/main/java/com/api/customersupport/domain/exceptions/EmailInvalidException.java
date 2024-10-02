package com.api.customersupport.domain.exceptions;

public class EmailInvalidException extends Exception {
    // Attributes
    private String code;

    public EmailInvalidException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
