package com.api.customersupport.domain.exceptions;

public class MappingException extends RuntimeException {
    // Attributes
    private String code;

    public MappingException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
