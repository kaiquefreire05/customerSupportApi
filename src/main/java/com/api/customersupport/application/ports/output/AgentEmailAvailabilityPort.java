package com.api.customersupport.application.ports.output;

public interface AgentEmailAvailabilityPort {
    Boolean isAgentEmailAvailable(String email);
}
