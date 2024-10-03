package com.api.customersupport.application.gateway.agent;

import com.api.customersupport.domain.models.Agent;

import java.util.UUID;

public interface FindAgentByIdGateway {
    Agent findAgentById(UUID agentId);
}
