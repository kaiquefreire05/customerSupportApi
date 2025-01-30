package com.api.customersupport.application.services.feedback;

import com.api.customersupport.application.mapper.FeedbackMapper;
import com.api.customersupport.application.ports.input.feedback.FindFeedbackByIdUseCase;
import com.api.customersupport.application.ports.input.feedback.UpdateFeedbackUseCase;
import com.api.customersupport.application.ports.output.FeedbackRepositoryPort;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.exceptions.RatingInvalidException;
import com.api.customersupport.domain.models.Feedback;

import java.time.LocalDateTime;

public class UpdateFeedbackService implements UpdateFeedbackUseCase {

    //Dependency injection
    private final FeedbackRepositoryPort feedbackRepositoryPort;
    private final FeedbackMapper feedbackMapper;
    private final FindFeedbackByIdUseCase findFeedbackByIdUseCase;

    public UpdateFeedbackService(FeedbackRepositoryPort feedbackRepositoryPort, FeedbackMapper feedbackMapper,
                                 FindFeedbackByIdUseCase findFeedbackByIdUseCase) {
        this.feedbackRepositoryPort = feedbackRepositoryPort;
        this.feedbackMapper = feedbackMapper;
        this.findFeedbackByIdUseCase = findFeedbackByIdUseCase;
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) throws RatingInvalidException, FeedbackNotFoundException {
        Feedback existentFeedback = findFeedbackByIdUseCase.findFeedbackById(feedback.getId());
        feedbackMapper.updateValues(feedback, existentFeedback);
        existentFeedback.setUpdatedAt(LocalDateTime.now());
        return feedbackRepositoryPort.updateFeedback(feedback);
    }
}
