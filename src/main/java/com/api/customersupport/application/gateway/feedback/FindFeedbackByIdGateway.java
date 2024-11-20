package com.api.customersupport.application.gateway.feedback;

import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;

public interface FindFeedbackByIdGateway {

    Feedback findFeedbackById(Long feedbackId) throws FeedbackNotFoundException;

}
