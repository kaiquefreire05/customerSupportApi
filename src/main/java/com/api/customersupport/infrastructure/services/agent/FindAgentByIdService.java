package com.api.customersupport.infrastructure.services.agent;

import com.api.customersupport.application.gateway.agent.FindAgentByIdGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.mapper.AgentMapper;
import com.api.customersupport.infrastructure.repositories.AgentEntityRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class FindAgentByIdService implements FindAgentByIdGateway {
    // Dependency Injection
    private final AgentEntityRepository agentRepository;
    private final AgentMapper agentMapper;

    public FindAgentByIdService(AgentEntityRepository agentRepository, AgentMapper agentMapper) {
        this.agentRepository = agentRepository;
        this.agentMapper = agentMapper;
    }

    @Override
    public Agent findAgentById(UUID agentId) throws AgentNotFoundException {
        log.info("Finding agent by id: {}::FindAgentByIdGatewayImpl", agentId);
        return agentRepository.findById(agentId)
                .map(agentMapper::toDomainModel)
                .orElseThrow(() -> new AgentNotFoundException(ErrorCodeEnum.ON0006.getCode()
                        , ErrorCodeEnum.ON0006.getMessage()));
    }

}
