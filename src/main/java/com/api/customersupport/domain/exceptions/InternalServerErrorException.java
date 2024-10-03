package com.api.customersupport.domain.exceptions;

public class InternalServerErrorException extends Exception {
    // Attributes
    private String code;

    public InternalServerErrorException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
