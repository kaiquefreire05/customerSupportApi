package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.agent.FindAgentByIdGateway;
import com.api.customersupport.domain.models.Agent;

import java.util.UUID;

public class FindAgentByIdGatewayImpl implements FindAgentByIdGateway {
    // Dependency Injection

    @Override
    public Agent findAgentById(UUID agentId) {
        return null;
    }
}
