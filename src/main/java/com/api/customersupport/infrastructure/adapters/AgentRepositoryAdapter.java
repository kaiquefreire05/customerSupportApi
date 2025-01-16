package com.api.customersupport.infrastructure.adapters;

import com.api.customersupport.application.ports.output.AgentRepositoryPort;
import com.api.customersupport.domain.models.Agent;

import java.util.List;
import java.util.UUID;

public class AgentRepositoryAdapter implements AgentRepositoryPort {
    @Override
    public Agent saveAgent(Agent agent) {
        return null;
    }

    @Override
    public Agent updateAgent(Agent agent) {
        return null;
    }

    @Override
    public Agent getAgentById(UUID id) {
        return null;
    }

    @Override
    public Agent getAgentByEmail(String email) {
        return null;
    }

    @Override
    public List<Agent> listAgents() {
        return List.of();
    }

    @Override
    public Boolean deleteAgent(UUID id) {
        return null;
    }
}
