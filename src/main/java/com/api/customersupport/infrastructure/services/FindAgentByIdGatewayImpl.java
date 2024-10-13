package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.agent.FindAgentByIdGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.MappingException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.entities.AgentEntity;
import com.api.customersupport.infrastructure.mapper.AgentMapper;
import com.api.customersupport.infrastructure.repositories.AgentEntityRepository;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

import java.util.UUID;

@Service
public class FindAgentByIdGatewayImpl implements FindAgentByIdGateway {
    // Dependency Injection
    private final AgentEntityRepository agentRepository;
    private final AgentMapper agentMapper;

    public FindAgentByIdGatewayImpl(AgentEntityRepository agentRepository, AgentMapper agentMapper) {
        this.agentRepository = agentRepository;
        this.agentMapper = agentMapper;
    }

    @Override
    public Agent findAgentById(UUID agentId) throws AgentNotFoundException {
        log.info("Finding agent by id: {}::FindAgentByIdGatewayImpl", agentId);
        return agentRepository.findById(agentId)
                .map(this::safeToDomainModel)
                .orElseThrow(() -> new AgentNotFoundException(ErrorCodeEnum.ON0006.getCode()
                        , ErrorCodeEnum.ON0006.getMessage()));
    }

    private Agent safeToDomainModel(AgentEntity agentEntity) {
        try {
            return agentMapper.toDomainModel(agentEntity);

        } catch (EmailInvalidException | PhoneInvalidException ex) {
            log.error("Error occurred while converting AgentEntity to Agent: {}::FindAgentByIdGatewayImpl"
                    , ex.getMessage());
            throw new MappingException(ErrorCodeEnum.MP0001.getCode()
                    , ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.MP0001));
        }
    }
}
