package com.api.customersupport.application.services.agent;

import com.api.customersupport.application.ports.input.agent.FindAgentByEmailUseCase;
import com.api.customersupport.application.ports.output.AgentRepositoryPort;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;

public class FindAgentByEmailService implements FindAgentByEmailUseCase {
    // Dependency Injection
    private final AgentRepositoryPort agentRepositoryPort;

    public FindAgentByEmailService(AgentRepositoryPort agentRepositoryPort) {
        this.agentRepositoryPort = agentRepositoryPort;
    }

    @Override
    public Agent findAgentByEmail(String email) throws AgentNotFoundException {
        return agentRepositoryPort.getAgentByEmail(email);
    }
}
