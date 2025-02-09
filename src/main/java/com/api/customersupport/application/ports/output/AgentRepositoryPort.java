package com.api.customersupport.application.ports.output;

import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;

import java.util.List;
import java.util.UUID;

public interface AgentRepositoryPort {
    Agent saveAgent(Agent agent);
    Agent updateAgent(Agent agent) throws AgentNotFoundException;
    Agent getAgentById(UUID id) throws AgentNotFoundException;
    Agent getAgentByEmail(String email) throws AgentNotFoundException;
    List<Agent> listAgents();
    Boolean deleteAgent(UUID id);
}
