package com.api.customersupport.application.services.feedback;

import com.api.customersupport.application.gateway.feedback.FindFeedbackByIdGateway;
import com.api.customersupport.application.ports.output.FeedbackRepositoryPort;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.application.ports.input.feedback.FindFeedbackByIdUseCase;

public class FindFeedbackByIdService implements FindFeedbackByIdUseCase {
    //Dependency injection
    private final FeedbackRepositoryPort feedbackRepositoryPort;

    public FindFeedbackByIdService(FeedbackRepositoryPort feedbackRepositoryPort) {
        this.feedbackRepositoryPort = feedbackRepositoryPort;
    }

    @Override
    public Feedback findFeedbackById(Long feedbackId) throws FeedbackNotFoundException {
        return feedbackRepositoryPort.findFeedbackById(feedbackId);
    }

}
