package com.api.customersupport.domain.exceptions;

public class FeedbackNotFoundException extends Exception {

    //Attributes
    private String code;

    public FeedbackNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}