package com.api.customersupport.application.usecaseimpl.feedback;

import com.api.customersupport.application.gateway.feedback.FindFeedbackByTicketIdGateway;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.usecases.feedback.FindFeedbackByTicketIdUseCase;

public class FindFeedbackByTicketIdImpl implements FindFeedbackByTicketIdUseCase {
    // Dependency Injection
    private final FindFeedbackByTicketIdGateway findFeedbackByTicketIdGateway;

    public FindFeedbackByTicketIdImpl(FindFeedbackByTicketIdGateway findFeedbackByTicketIdGateway) {
        this.findFeedbackByTicketIdGateway = findFeedbackByTicketIdGateway;
    }

    @Override
    public Feedback findFeedbackByTicketId(Long ticketId) throws FeedbackNotFoundException {
        return findFeedbackByTicketIdGateway.findFeedbackByTicketId(ticketId);
    }
}
