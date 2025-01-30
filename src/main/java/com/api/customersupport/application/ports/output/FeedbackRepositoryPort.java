package com.api.customersupport.application.ports.output;

import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;

import java.util.List;

public interface FeedbackRepositoryPort {
    Boolean deleteFeedback(Long id);
    Feedback findFeedbackById(Long id) throws FeedbackNotFoundException;
    Feedback findFeedbackByTicketId(Long ticketId) throws FeedbackNotFoundException;
    Boolean provideFeedback(Feedback feedback);
    Feedback updateFeedback(Feedback feedback);
    List<Feedback> listFeedbacks();
}
