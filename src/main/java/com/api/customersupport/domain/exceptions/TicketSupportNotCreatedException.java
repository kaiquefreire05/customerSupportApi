package com.api.customersupport.domain.exceptions;

public class TicketSupportNotCreatedException extends Exception {
    // Attributes
    private String code;

    public TicketSupportNotCreatedException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
