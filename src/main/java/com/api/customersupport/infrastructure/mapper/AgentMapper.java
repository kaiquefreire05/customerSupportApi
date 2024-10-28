package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.MappingException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.entities.AgentEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AgentMapper {
    // Dependency Injection
    private final SupportTicketMapper supportTicketMapper;

    public AgentMapper(@Lazy SupportTicketMapper supportTicketMapper) {
        this.supportTicketMapper = supportTicketMapper;
    }

    // Methods
    public Agent toDomainModel(AgentEntity agentEntity) {
        try {
            return new Agent(
                agentEntity.getId(),
                agentEntity.getName(),
                agentEntity.getEmail(),
                agentEntity.getPhone(),
                agentEntity.getPassword(),
                agentEntity.getCreatedAt(),
                agentEntity.getUpdatedAt(),
                supportTicketMapper.toDomainModelList(agentEntity.getAssignedTickets())
            );

        } catch (EmailInvalidException | PhoneInvalidException ex) {
            throw new MappingException(ErrorCodeEnum.MP0001.getCode(),
                    ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.MP0001));
        }

    }

    public AgentEntity toEntity(Agent agent) {
        return new AgentEntity(
                agent.getId(),
                agent.getName(),
                agent.getEmail(),
                agent.getPhone(),
                agent.getPassword(),
                agent.getCreatedAt(),
                agent.getUpdatedAt(),
                supportTicketMapper.toEntityList(agent.getAssignedTickets())
        );
    }

}
