package com.api.customersupport.application.services.agent;

import com.api.customersupport.application.ports.input.agent.ListAgentsUseCase;
import com.api.customersupport.application.ports.output.AgentRepositoryPort;
import com.api.customersupport.domain.models.Agent;

import java.util.List;

public class ListAgentsService implements ListAgentsUseCase {
    // Dependency Injection
    private final AgentRepositoryPort agentRepositoryPort;

    public ListAgentsService(AgentRepositoryPort agentRepositoryPort) {
        this.agentRepositoryPort = agentRepositoryPort;
    }

    @Override
    public List<Agent> listAgents() {
        return agentRepositoryPort.listAgents();
    }
}
