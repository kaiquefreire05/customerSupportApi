package com.api.customersupport.domain.exceptions;

public class JwtCreateException extends Exception {
    // Attributes
    private String code;

    public JwtCreateException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
