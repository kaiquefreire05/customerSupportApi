package com.api.customersupport.application.usecaseimpl.feedback;

import com.api.customersupport.application.gateway.feedback.FindFeedbackByIdGateway;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.usecases.feedback.FindFeedbackByIdUseCase;

public class FindFeedbackByIdImpl implements FindFeedbackByIdUseCase {
    //Dependency injection
    private final FindFeedbackByIdGateway findFeedbackByIdGateway;

    public FindFeedbackByIdImpl(FindFeedbackByIdGateway findFeedbackByIdGateway) {
        this.findFeedbackByIdGateway = findFeedbackByIdGateway;
    }

    @Override
    public Feedback findFeedbackById(Long feedbackId) throws FeedbackNotFoundException {
        return findFeedbackByIdGateway.findFeedbackById(feedbackId);
    }

}
