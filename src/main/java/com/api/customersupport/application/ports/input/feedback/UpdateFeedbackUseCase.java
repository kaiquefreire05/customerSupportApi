package com.api.customersupport.application.ports.input.feedback;

import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.exceptions.RatingInvalidException;
import com.api.customersupport.domain.models.Feedback;

public interface UpdateFeedbackUseCase {

    Feedback updateFeedback(Feedback feedback) throws RatingInvalidException, FeedbackNotFoundException;

}
