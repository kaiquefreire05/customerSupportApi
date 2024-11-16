package com.api.customersupport.application.usecaseimpl.agent;

import com.api.customersupport.application.gateway.agent.UpdateAgentGateway;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.usecases.agent.UpdateAgentUseCase;

public class UpdateAgentImpl implements UpdateAgentUseCase {
    // Dependency Injection
    private final UpdateAgentGateway updateAgentGateway;

    public UpdateAgentImpl(UpdateAgentGateway updateAgentGateway) {
        this.updateAgentGateway = updateAgentGateway;
    }

    @Override
    public Agent updateAgent(Agent agent) {
        return updateAgentGateway.updateAgent(agent);
    }
}
