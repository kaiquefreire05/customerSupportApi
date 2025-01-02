package com.api.customersupport.infrastructure.services.feedback;

import com.api.customersupport.application.gateway.feedback.DeleteFeedbackGateway;
import com.api.customersupport.infrastructure.repositories.FeedbackEntityRepository;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteFeedbackService implements DeleteFeedbackGateway {

    //Dependency injection
    private final FeedbackEntityRepository feedbackEntityRepository;

    public DeleteFeedbackService(FeedbackEntityRepository feedbackEntityRepository) {
        this.feedbackEntityRepository = feedbackEntityRepository;
    }

    @Override
    public boolean deleteFeedback(Long feedbackId) {
        try {
            log.info("Deleting feedback with id: {}", feedbackId);
            feedbackEntityRepository.deleteById(feedbackId);
            log.info("Feedback with id: {} deleted successfully", feedbackId);
            return true;
        } catch (IllegalArgumentException ex) {
            log.error("Invalid feedback ID: {}. Error details: {}", feedbackId, ex.getMessage());
            return false;
        } catch (Exception ex) {
            log.error("Failed to delete feedback with id: {}. Error details: {}", feedbackId, ex.getMessage());
            return false;
        }
    }

}
