package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.MappingException;
import com.api.customersupport.domain.exceptions.RatingInvalidException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.infrastructure.entities.FeedbackEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {
    // Dependency Injection
    private final SupportTicketMapper supportTicketMapper;

    public FeedbackMapper(@Lazy SupportTicketMapper supportTicketMapper) {
        this.supportTicketMapper = supportTicketMapper;
    }

    // Methods
    public Feedback toDomainModel(FeedbackEntity feedbackEntity) {
        try {
            return new Feedback(
                    feedbackEntity.getId(),
                    feedbackEntity.getComments(),
                    feedbackEntity.getRating(),
                    feedbackEntity.getCreatedAt(),
                    feedbackEntity.getUpdatedAt(),
                    supportTicketMapper.toDomainModel(feedbackEntity.getSupportTicket())
            );
        } catch (RatingInvalidException ex) {
            throw new MappingException(ErrorCodeEnum.MP0001.getCode(),
                    ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.MP0001));
        }
    }

    public FeedbackEntity toEntity(Feedback feedback) {
        return new FeedbackEntity(
                feedback.getId(),
                feedback.getComments(),
                feedback.getRating(),
                feedback.getCreatedAt(),
                feedback.getUpdatedAt(),
                supportTicketMapper.toEntity(feedback.getSupportTicket())
        );
    }
}
