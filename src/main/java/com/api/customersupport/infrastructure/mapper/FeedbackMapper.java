package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.exceptions.RatingInvalidException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.infrastructure.entities.FeedbackEntity;

public class FeedbackMapper {
    // Dependency Injection
    private final SupportTicketMapper supportTicketMapper;

    public FeedbackMapper(SupportTicketMapper supportTicketMapper) {
        this.supportTicketMapper = supportTicketMapper;
    }

    // Methods
    public Feedback toDomainModel(FeedbackEntity feedbackEntity) throws RatingInvalidException, EmailInvalidException
            , PhoneInvalidException {
        return new Feedback(
                feedbackEntity.getId(),
                feedbackEntity.getComments(),
                feedbackEntity.getRating(),
                feedbackEntity.getCreatedAt(),
                feedbackEntity.getUpdatedAt(),
                supportTicketMapper.toDomainModel(feedbackEntity.getSupportTicket())
        );
    }
}
