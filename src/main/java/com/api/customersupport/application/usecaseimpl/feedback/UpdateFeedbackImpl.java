package com.api.customersupport.application.usecaseimpl.feedback;

import com.api.customersupport.application.gateway.feedback.UpdateFeedbackGateway;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.usecases.feedback.UpdateFeedbackUseCase;

public class UpdateFeedbackImpl implements UpdateFeedbackUseCase {

    //Dependency injection
    private final UpdateFeedbackGateway updateFeedbackGateway;

    public UpdateFeedbackImpl(UpdateFeedbackGateway updateFeedbackGateway) {
        this.updateFeedbackGateway = updateFeedbackGateway;
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        return updateFeedbackGateway.updateFeedback(feedback);
    }

}
