package com.api.customersupport.domain.exceptions;

public class ClientNotFoundException extends Exception {
    // Attributes
    private String code;

    public ClientNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
