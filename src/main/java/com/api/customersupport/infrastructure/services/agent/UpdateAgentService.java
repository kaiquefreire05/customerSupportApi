package com.api.customersupport.infrastructure.services.agent;

import com.api.customersupport.application.gateway.agent.UpdateAgentGateway;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.infrastructure.jpa.AgentEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateAgentService implements UpdateAgentGateway {
    // Dependency Injection
    private final AgentEntityRepository agentEntityRepository;
    private final AgentMapper agentMapper;
    private final PasswordEncoder passwordEncoder;

    public UpdateAgentService(AgentEntityRepository agentEntityRepository, AgentMapper agentMapper
            , PasswordEncoder passwordEncoder) {
        this.agentEntityRepository = agentEntityRepository;
        this.agentMapper = agentMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Agent updateAgent(Agent agent) {
        String encodedPassword = passwordEncoder.encode(agent.getPassword());
        agent.setPassword(encodedPassword);
        var agentEntity = agentMapper.toEntityUpdate(agent);
        var updatedAgentEntity = agentEntityRepository.save(agentEntity);
        return agentMapper.toDomainModel(updatedAgentEntity);
    }
}
