package com.api.customersupport.application.services.feedback;

import com.api.customersupport.application.ports.input.feedback.FindFeedbackByTicketIdUseCase;
import com.api.customersupport.application.ports.output.FeedbackRepositoryPort;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;

public class FindFeedbackByTicketIdService implements FindFeedbackByTicketIdUseCase {
    // Dependency Injection
    private final FeedbackRepositoryPort feedbackRepositoryPort;

    public FindFeedbackByTicketIdService(FeedbackRepositoryPort feedbackRepositoryPort) {
        this.feedbackRepositoryPort = feedbackRepositoryPort;
    }

    @Override
    public Feedback findFeedbackByTicketId(Long ticketId) throws FeedbackNotFoundException {
        return feedbackRepositoryPort.findFeedbackByTicketId(ticketId);
    }
}
