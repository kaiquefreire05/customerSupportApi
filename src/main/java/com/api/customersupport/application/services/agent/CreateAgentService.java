package com.api.customersupport.application.services.agent;

import com.api.customersupport.application.ports.input.agent.CreateAgentUseCase;
import com.api.customersupport.application.ports.output.availability.AgentEmailAvailabilityPort;
import com.api.customersupport.application.ports.output.AgentRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.domain.models.Agent;

public class CreateAgentService implements CreateAgentUseCase {
    // Dependency Injection
    private final AgentRepositoryPort agentRepositoryPort;
    private final AgentEmailAvailabilityPort agentEmailAvailabilityPort;

    public CreateAgentService(AgentRepositoryPort agentRepositoryPort, AgentEmailAvailabilityPort agentEmailAvailabilityPort) {
        this.agentRepositoryPort = agentRepositoryPort;
        this.agentEmailAvailabilityPort = agentEmailAvailabilityPort;
    }

    @Override
    public void createAgent(Agent agent) throws EmailUnavailableException {
        checkEmailAvailability(agent.getEmail());
        agentRepositoryPort.saveAgent(agent);
    }

    // Helper Methods
    public void checkEmailAvailability(String email) throws EmailUnavailableException {
        if (!agentEmailAvailabilityPort.isAgentEmailAvailable(email)) {
           throw new EmailUnavailableException(ErrorCodeEnum.ON0007.getCode(), ErrorCodeEnum.ON0007.getMessage());
        }
    }
}
