package com.api.customersupport.application.gateway.agent;

import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;

public interface FindAgentByEmailGateway {
    Agent findAgentByEmail(String email) throws AgentNotFoundException;
}
