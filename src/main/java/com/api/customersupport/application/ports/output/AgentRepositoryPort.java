package com.api.customersupport.application.ports.output;

import com.api.customersupport.domain.models.Agent;

import java.util.List;
import java.util.UUID;

public interface AgentRepositoryPort {
    Agent saveAgent(Agent agent);
    Agent updateAgent(Agent agent);
    Agent getAgentById(UUID id);
    Agent getAgentByEmail(String email);
    List<Agent> listAgents();
    Boolean deleteAgent(UUID id);
}
