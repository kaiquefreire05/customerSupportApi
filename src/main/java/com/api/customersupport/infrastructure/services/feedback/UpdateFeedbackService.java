package com.api.customersupport.infrastructure.services.feedback;

import com.api.customersupport.application.gateway.feedback.UpdateFeedbackGateway;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.infrastructure.entities.FeedbackEntity;
import com.api.customersupport.infrastructure.mapper.FeedbackMapper;
import com.api.customersupport.infrastructure.repositories.FeedbackEntityRepository;

public class UpdateFeedbackService implements UpdateFeedbackGateway {

    //Dependency injection
    private final FeedbackMapper feedbackMapper;
    private final FeedbackEntityRepository feedbackEntityRepository;

    public UpdateFeedbackService(FeedbackMapper feedbackMapper, FeedbackEntityRepository feedbackEntityRepository) {
        this.feedbackMapper = feedbackMapper;
        this.feedbackEntityRepository = feedbackEntityRepository;
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        FeedbackEntity feedbackEntity = feedbackMapper.toEntityUpdate(feedback);
        FeedbackEntity savedFeedbackEntity = feedbackEntityRepository.save(feedbackEntity);
        return feedbackMapper.toDomainModel(savedFeedbackEntity);
    }

}
