package com.api.customersupport.application.usecaseimpl.agent;

import com.api.customersupport.application.gateway.agent.ListAgentsGateway;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.usecases.agent.ListAgentsUseCase;

import java.util.List;

public class ListAgentsImpl implements ListAgentsUseCase {
    // Dependency Injection
    private final ListAgentsGateway listAgentsGateway;

    public ListAgentsImpl(ListAgentsGateway listAgentsGateway) {
        this.listAgentsGateway = listAgentsGateway;
    }

    @Override
    public List<Agent> listAgents() {
        return listAgentsGateway.listAgents();
    }
}
