package com.api.customersupport.application.usecaseimpl.agent;

import com.api.customersupport.application.gateway.agent.FindAgentByEmailGateway;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.usecases.agent.FindAgentByEmailUseCase;

public class FindAgentByEmailImpl implements FindAgentByEmailUseCase {
    // Dependency Injection
    private final FindAgentByEmailGateway findAgentByEmailGateway;

    public FindAgentByEmailImpl(FindAgentByEmailGateway findAgentByEmailGateway) {
        this.findAgentByEmailGateway = findAgentByEmailGateway;
    }

    @Override
    public Agent findAgentByEmail(String email) throws AgentNotFoundException {
        return findAgentByEmailGateway.findAgentByEmail(email);
    }
}
