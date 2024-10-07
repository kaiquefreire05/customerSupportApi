package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.exceptions.RatingInvalidException;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class SupportTicketMapper {
    //Dependency Injection
    private final AgentMapper agentMapper;
    private final ClientMapper clientMapper;
    private final FeedbackMapper feedbackMapper;

    public SupportTicketMapper(AgentMapper agentMapper, ClientMapper clientMapper, FeedbackMapper feedbackMapper) {
        this.agentMapper = agentMapper;
        this.clientMapper = clientMapper;
        this.feedbackMapper = feedbackMapper;
    }

    // Methods
    public SupportTicket toDomainModel(SupportTicketEntity supportTicketEntity) throws EmailInvalidException
            , PhoneInvalidException, RatingInvalidException {
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
    }


    @SneakyThrows
    public List<SupportTicket> toDomainModelList(List<SupportTicketEntity> supportTicketEntities) {
        return supportTicketEntities.stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }
}
