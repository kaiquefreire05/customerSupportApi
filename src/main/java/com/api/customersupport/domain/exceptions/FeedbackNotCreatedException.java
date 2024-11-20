package com.api.customersupport.domain.exceptions;

public class FeedbackNotCreatedException extends Exception {
    // Attributes
    private String code;

    public FeedbackNotCreatedException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
