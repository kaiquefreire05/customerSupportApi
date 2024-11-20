package com.api.customersupport.application.gateway.feedback;

import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;

public interface FindFeedbackByTicketIdGateway {
    Feedback findFeedbackByTicketId(Long ticketId) throws FeedbackNotFoundException;
}
