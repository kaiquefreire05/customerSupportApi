package com.api.customersupport.usecases.feedback;

import com.api.customersupport.domain.models.Feedback;

public interface ProvideFeedbackUseCase {
    void provideFeedback(String ticketId, Feedback feedback);
}
