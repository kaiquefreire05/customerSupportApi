package com.api.customersupport.infrastructure.services.agent;

import com.api.customersupport.application.gateway.agent.DeleteAgentGateway;
import com.api.customersupport.infrastructure.repositories.AgentEntityRepository;

import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteAgentGatewayImpl implements DeleteAgentGateway {
    // Dependency Injection
    private final AgentEntityRepository agentEntityRepository;

    public DeleteAgentGatewayImpl(AgentEntityRepository agentEntityRepository) {
        this.agentEntityRepository = agentEntityRepository;
    }

    @Override
    public boolean deleteAgent(UUID agentId) {
        try {
            log.info("Deleting agent with id: {}", agentId);
            agentEntityRepository.deleteById(agentId);
            log.info("Agent with id: {} deleted successfully", agentId);
            return true;
        } catch (IllegalArgumentException ex) {
            log.error("Invalid agent ID: {}. Error details: {}", agentId, ex.getMessage());
            return false;
        } catch (Exception ex) {
            log.error("Failed to delete agent with id: {}. Error details: {}", agentId, ex.getMessage());
            return false;
        }
    }
}
