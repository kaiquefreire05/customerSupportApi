package com.api.customersupport.application.ports.input.agent;

import com.api.customersupport.domain.models.Agent;

import java.util.List;

public interface ListAgentsUseCase {
    List<Agent> listAgents();
}
