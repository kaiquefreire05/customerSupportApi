package com.api.customersupport.domain.exceptions;

public class EntityNotFoundException extends Exception {
    // Attributes
    private String code;

    public EntityNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
