package com.api.customersupport.application.ports.input.agent;

import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;

public interface FindAgentByEmailUseCase {
    Agent findAgentByEmail(String email) throws AgentNotFoundException;
}
