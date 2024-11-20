package com.api.customersupport.usecases.feedback;

import com.api.customersupport.domain.exceptions.FeedbackNotCreatedException;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.Feedback;

public interface ProvideFeedbackUseCase {
    void provideFeedback(Long ticketId, Feedback feedback) throws TicketSupportNotFoundException, InternalServerErrorException, FeedbackNotCreatedException;
}
