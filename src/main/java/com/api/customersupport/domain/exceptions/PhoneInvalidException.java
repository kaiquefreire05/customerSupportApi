package com.api.customersupport.domain.exceptions;

public class PhoneInvalidException extends Exception {
    // Attributes
    private String code;

    public PhoneInvalidException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
