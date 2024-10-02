package com.api.customersupport.domain.exceptions;

public class RatingInvalidException extends Exception {
    // Attributes
    private String code;

    public RatingInvalidException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
