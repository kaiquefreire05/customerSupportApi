package com.api.customersupport.application.services.feedback;

import com.api.customersupport.application.gateway.feedback.FindFeedbackByTicketIdGateway;
import com.api.customersupport.application.ports.output.FeedbackRepositoryPort;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.application.ports.input.feedback.FindFeedbackByTicketIdUseCase;

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
