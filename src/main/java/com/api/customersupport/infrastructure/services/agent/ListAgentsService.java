package com.api.customersupport.infrastructure.services.agent;

import com.api.customersupport.application.gateway.agent.ListAgentsGateway;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.entities.AgentEntity;
import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.infrastructure.repositories.AgentEntityRepository;

import java.util.List;

public class ListAgentsService implements ListAgentsGateway {
    // Dependency Injection
    private final AgentEntityRepository agentEntityRepository;
    private final AgentMapper agentMapper;

    public ListAgentsService(AgentEntityRepository agentEntityRepository, AgentMapper agentMapper) {
        this.agentEntityRepository = agentEntityRepository;
        this.agentMapper = agentMapper;
    }

    @Override
    public List<Agent> listAgents() {
        List<AgentEntity> agentEntities = agentEntityRepository.findAll();
        return agentMapper.toDomainModelList(agentEntities);
    }
}
