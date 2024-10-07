package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.entities.AgentEntity;

public class AgentMapper {
    // Dependency Injection
    private final SupportTicketMapper supportTicketMapper;

    public AgentMapper(SupportTicketMapper supportTicketMapper) {
        this.supportTicketMapper = supportTicketMapper;
    }

    // Methods
    public Agent toDomainModel(AgentEntity agentEntity) throws EmailInvalidException, PhoneInvalidException {
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
    }

}
