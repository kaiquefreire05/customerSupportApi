package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupportTicketMapper {
    //Dependency Injection
    private final AgentMapper agentMapper;
    private final ClientMapper clientMapper;
    private final FeedbackMapper feedbackMapper;

    public SupportTicketMapper(AgentMapper agentMapper, @Lazy ClientMapper clientMapper, FeedbackMapper feedbackMapper) {
        this.agentMapper = agentMapper;
        this.clientMapper = clientMapper;
        this.feedbackMapper = feedbackMapper;
    }

    // Methods
    public SupportTicket toDomainModel(SupportTicketEntity supportTicketEntity) {
        return new SupportTicket(
                supportTicketEntity.getId(),
                supportTicketEntity.getTitle(),
                supportTicketEntity.getDescription(),
                supportTicketEntity.getStatus(),
                supportTicketEntity.getCategory(),
                supportTicketEntity.getClosedAt(),
                supportTicketEntity.getCreatedAt(),
                supportTicketEntity.getUpdatedAt(),
                clientMapper.toDomainModel(supportTicketEntity.getClient()),
                supportTicketEntity.getAssignedAgent() != null ? agentMapper.toDomainModel(supportTicketEntity.getAssignedAgent()) : null,
                supportTicketEntity.getFeedback() != null ? feedbackMapper.toDomainModel(supportTicketEntity.getFeedback()) : null
        );
    }

    public SupportTicketEntity toEntity(SupportTicket supportTicket) {
        return new SupportTicketEntity(
                supportTicket.getId(),
                supportTicket.getTitle(),
                supportTicket.getDescription(),
                supportTicket.getStatus(),
                supportTicket.getCategory(),
                supportTicket.getClosedAt(),
                supportTicket.getCreatedAt(),
                supportTicket.getUpdatedAt(),
                clientMapper.toEntity(supportTicket.getClient()),
                agentMapper.toEntity(supportTicket.getAssignedAgent()),
                feedbackMapper.toEntity(supportTicket.getFeedback())
        );
    }

    public SupportTicketEntity toEntityWithoutRelations(SupportTicket supportTicket) {
        return new SupportTicketEntity(
                supportTicket.getId(),
                supportTicket.getTitle(),
                supportTicket.getDescription(),
                supportTicket.getStatus(),
                supportTicket.getCategory(),
                supportTicket.getClosedAt(),
                supportTicket.getCreatedAt(),
                supportTicket.getUpdatedAt(),
                clientMapper.toEntity(supportTicket.getClient()),
                null,
                null
        );
    }

    public List<SupportTicket> toDomainModelList(List<SupportTicketEntity> supportTicketEntities) {
        return supportTicketEntities.stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    public List<SupportTicketEntity> toEntityList(List<SupportTicket> supportTickets) {
        return supportTickets.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public SupportTicketEntity toEntityUpdate(SupportTicket supportTicket) {
        return new SupportTicketEntity(
                supportTicket.getId(),
                supportTicket.getTitle(),
                supportTicket.getDescription(),
                supportTicket.getStatus(),
                supportTicket.getCategory(),
                supportTicket.getClosedAt(),
                supportTicket.getCreatedAt(),
                supportTicket.getUpdatedAt(),
                clientMapper.toEntity(supportTicket.getClient()),
                supportTicket.getAssignedAgent() != null ? agentMapper.toEntity(supportTicket.getAssignedAgent()) : null,
                supportTicket.getFeedback() != null ? feedbackMapper.toEntity(supportTicket.getFeedback()) : null
        );
    }

}
