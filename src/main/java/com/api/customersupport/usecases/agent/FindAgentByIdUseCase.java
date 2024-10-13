package com.api.customersupport.usecases.agent;

import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;

import java.util.UUID;

public interface FindAgentByIdUseCase {
    Agent findAgentById(UUID agentId) throws AgentNotFoundException;
}
