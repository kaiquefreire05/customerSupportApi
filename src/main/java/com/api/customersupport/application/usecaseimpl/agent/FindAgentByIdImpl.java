package com.api.customersupport.application.usecaseimpl.agent;

import com.api.customersupport.application.gateway.agent.FindAgentByIdGateway;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.usecases.agent.FindAgentByIdUseCase;

import java.util.UUID;

public class FindAgentByIdImpl implements FindAgentByIdUseCase {
    // Dependency Injection
    private final FindAgentByIdGateway findAgentByIdGateway;

    public FindAgentByIdImpl(FindAgentByIdGateway findAgentByIdGateway) {
        this.findAgentByIdGateway = findAgentByIdGateway;
    }

    @Override
    public Agent findAgentById(UUID agentId) {
        return findAgentByIdGateway.findAgentById(agentId);
    }
}
