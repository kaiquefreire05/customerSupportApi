package com.api.customersupport.usecases.agent;

import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;

public interface FindAgentByEmailUseCase {
    Agent findAgentByEmail(String email) throws AgentNotFoundException;
}
