package com.api.customersupport.application.ports.input.feedback;

import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;

public interface FindFeedbackByIdUseCase {
    Feedback findFeedbackById(Long feedbackId) throws FeedbackNotFoundException;
}
