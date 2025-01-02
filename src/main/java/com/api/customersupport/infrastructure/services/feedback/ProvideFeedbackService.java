package com.api.customersupport.infrastructure.services.feedback;

import com.api.customersupport.application.gateway.feedback.ProvideFeedbackGateway;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.infrastructure.mapper.FeedbackMapper;
import com.api.customersupport.infrastructure.repositories.FeedbackEntityRepository;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class ProvideFeedbackService implements ProvideFeedbackGateway {
    // Dependency Injection
    private final FeedbackEntityRepository feedbackEntityRepository;
    private final FeedbackMapper feedbackMapper;

    public ProvideFeedbackService(FeedbackEntityRepository feedbackEntityRepository
            , FeedbackMapper feedbackMapper) {
        this.feedbackEntityRepository = feedbackEntityRepository;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public boolean provideFeedback(Feedback feedback) {
        try {
            log.info("Starting of feedback creation::ProvideFeedbackGatewayImpl");
            var feedbackSaved = feedbackEntityRepository.save(feedbackMapper.toEntity(feedback));
            log.info("End of feedback creation::ProvideFeedbackGatewayImpl");
            return true;
        } catch (Exception ex) {
            log.error("Error in feedback creation. Error details: {}::ProvideFeedbackGatewayImpl", ex.getMessage());
            return false;
        }
    }
}