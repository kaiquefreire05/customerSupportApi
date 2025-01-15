package com.api.customersupport.application.ports.output;

import com.api.customersupport.domain.models.Feedback;

import java.util.List;

public interface FeedbackRepositoryPort {
    Boolean deleteFeedback(Long id);
    Feedback findFeedbackById(Long id);
    Feedback findFeedbackByTicketId(Long ticketId);
    Boolean provideFeedback(Feedback feedback);
    Feedback updateFeedback(Feedback feedback);
    List<Feedback> listFeedbacks();
}
