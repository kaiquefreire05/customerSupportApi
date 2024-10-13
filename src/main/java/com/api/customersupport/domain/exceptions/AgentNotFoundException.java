package com.api.customersupport.domain.exceptions;

public class AgentNotFoundException extends Exception {
    // Attributes
    private String code;

    public AgentNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    // Getter
    public String getCode() {
        return code;
    }
}
