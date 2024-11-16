package com.api.customersupport.infrastructure.services.agent;

import com.api.customersupport.application.gateway.agent.AgentEmailAvailableGateway;
import com.api.customersupport.infrastructure.repositories.AgentEntityRepository;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class AgentEmailAvailableGatewayImpl implements AgentEmailAvailableGateway {
    // Dependency Injection
    private final AgentEntityRepository agentEntityRepository;

    public AgentEmailAvailableGatewayImpl(AgentEntityRepository agentEntityRepository) {
        this.agentEntityRepository = agentEntityRepository;
    }

    @Override
    public Boolean isEmailAvailable(String email) {
        log.info("Start of isEmailAvailable method::AgentEmailAvailableGatewayImpl");
        return !agentEntityRepository.existsByEmail(email);
    }
}
