package com.api.customersupport.usecases.feedback;

import com.api.customersupport.domain.models.Feedback;

public interface FindFeedbackByTicketIdUseCase {
    Feedback findFeedbackByTicketId(Long ticketId);
}
