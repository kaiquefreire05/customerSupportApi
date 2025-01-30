package com.api.customersupport.application.services.agent;

import com.api.customersupport.application.ports.input.agent.FindAgentByIdUseCase;
import com.api.customersupport.application.ports.output.AgentRepositoryPort;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;

import java.util.UUID;

public class FindAgentByIdService implements FindAgentByIdUseCase {
    // Dependency Injection
    private final AgentRepositoryPort agentRepositoryPort;

    public FindAgentByIdService(AgentRepositoryPort agentRepositoryPort) {
        this.agentRepositoryPort = agentRepositoryPort;
    }

    @Override
    public Agent findAgentById(UUID agentId) throws AgentNotFoundException {
        return agentRepositoryPort.getAgentById(agentId);
    }
}
