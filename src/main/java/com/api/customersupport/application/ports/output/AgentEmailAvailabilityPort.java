package com.api.customersupport.application.ports.output;

public interface AgentEmailAvailabilityPort {
    Boolean isEmailAvailable(String email);
}
