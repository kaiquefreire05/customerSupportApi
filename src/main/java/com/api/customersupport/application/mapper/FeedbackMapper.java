package com.api.customersupport.application.mapper;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.MappingException;
import com.api.customersupport.domain.exceptions.RatingInvalidException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.FeedbackEntity;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
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
    public void updateValues(Feedback feedback, Feedback existentFeedback) throws RatingInvalidException {
        existentFeedback.setComments(feedback.getComments());
        existentFeedback.setRating(feedback.getRating());
    }

    public Feedback toDomainModel(FeedbackEntity feedbackEntity) {
        try {
            return new Feedback(
                    feedbackEntity.getId(),
                    feedbackEntity.getComments(),
                    feedbackEntity.getRating(),
                    feedbackEntity.getCreatedAt(),
                    feedbackEntity.getUpdatedAt(),
                    new SupportTicket(feedbackEntity.getSupportTicket().getId())
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
        new SupportTicketEntity(feedback.getSupportTicket().getId())
    );
}

    public FeedbackEntity toEntityUpdate(Feedback feedback) {
        return new FeedbackEntity(
                feedback.getId(),
                feedback.getComments(),
                feedback.getRating(),
                feedback.getCreatedAt(),
                feedback.getUpdatedAt(),
                new SupportTicketEntity(feedback.getSupportTicket().getId())
        );
    }

}
