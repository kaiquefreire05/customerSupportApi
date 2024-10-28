package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.MappingException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.exceptions.RatingInvalidException;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;


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
        try {
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
                agentMapper.toDomainModel(supportTicketEntity.getAssignedAgent()),
                feedbackMapper.toDomainModel(supportTicketEntity.getFeedback())
            );
        } catch (EmailInvalidException | PhoneInvalidException | RatingInvalidException ex) {
            throw new MappingException(ErrorCodeEnum.MP0001.getCode(),
                    ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.MP0001));
        }
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
            agentMapper.toEntity(supportTicket.getAssignedAgent()),
            feedbackMapper.toEntity(supportTicket.getFeedback())
        );
    }
}
