package com.api.customersupport.application.ports.output.availability;

public interface AgentEmailAvailabilityPort {
    Boolean isAgentEmailAvailable(String email);
}
