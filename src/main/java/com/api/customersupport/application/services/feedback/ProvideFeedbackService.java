package com.api.customersupport.application.services.feedback;

import com.api.customersupport.application.ports.input.feedback.ProvideFeedbackUseCase;
import com.api.customersupport.application.ports.input.ticket.FindTicketByIdUseCase;
import com.api.customersupport.application.ports.output.FeedbackRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.FeedbackNotCreatedException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.domain.models.SupportTicket;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class ProvideFeedbackService implements ProvideFeedbackUseCase {

    //Dependency injection
    private final FeedbackRepositoryPort repository;
    private final FindTicketByIdUseCase findTicketById;

    public ProvideFeedbackService(FeedbackRepositoryPort repository, FindTicketByIdUseCase findTicketById) {
        this.repository = repository;
        this.findTicketById = findTicketById;
    }


    @Override
    public void provideFeedback(Long ticketId, Feedback feedback) throws FeedbackNotCreatedException {
        try {
            SupportTicket ticket = findTicketById.findTicketById(ticketId);
            feedback.setSupportTicket(ticket);
            boolean success = repository.provideFeedback(feedback);
            if (!success) {
                throw new FeedbackNotCreatedException(ErrorCodeEnum.FD0003.getCode(), ErrorCodeEnum.FD0003.getMessage());
            }

        } catch (Exception ex) {
            log.info("Error providing feedback with ID: {}. Error details: {}::ProvideFeedbackImpl", ticketId, ex.getMessage());
            throw new FeedbackNotCreatedException(ErrorCodeEnum.FD0003.getCode(),
                    ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.FD0003));
        }
    }
}
