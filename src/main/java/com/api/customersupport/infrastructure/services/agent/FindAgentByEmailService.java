package com.api.customersupport.infrastructure.services.agent;

import com.api.customersupport.application.gateway.agent.FindAgentByEmailGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.infrastructure.jpa.AgentEntityRepository;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class FindAgentByEmailService implements FindAgentByEmailGateway {
    // Dependency Injection
    private final AgentEntityRepository agentRepository;
    private final AgentMapper agentMapper;

    public FindAgentByEmailService(AgentEntityRepository agentRepository, AgentMapper agentMapper) {
        this.agentRepository = agentRepository;
        this.agentMapper = agentMapper;
    }

    @Override
    public Agent findAgentByEmail(String email) throws AgentNotFoundException {
        log.info("Finding agent by email: {}::FindAgentByEmailGatewayImpl", email);
        return agentRepository.findByEmail(email)
                .map(agentMapper::toDomainModel)
                .orElseThrow(() -> new AgentNotFoundException(ErrorCodeEnum.ON0006.getCode()
                        , ErrorCodeEnum.ON0006.getMessage()));
    }

}
