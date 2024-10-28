package com.api.customersupport.domain.exceptions;

public class EmailUnavailableException extends Exception {
    // Attributes
    private String code;

    public EmailUnavailableException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
