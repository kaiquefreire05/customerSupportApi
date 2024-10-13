package com.api.customersupport.domain.exceptions;

public class TicketSupportNotFoundException extends Exception {
    // Attributes
    private String code;

    public TicketSupportNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
