package com.api.customersupport.application.usecaseimpl.feedback;

import com.api.customersupport.application.gateway.feedback.ProvideFeedbackGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.FeedbackNotCreatedException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.feedback.ProvideFeedbackUseCase;
import com.api.customersupport.usecases.ticket.FindTicketByIdUseCase;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class ProvideFeedbackImpl implements ProvideFeedbackUseCase {

    //Dependency injection
    private final ProvideFeedbackGateway provideFeedbackGateway;
    private final FindTicketByIdUseCase findTicketByIdGateway;

    public ProvideFeedbackImpl(ProvideFeedbackGateway provideFeedbackGateway
            , FindTicketByIdUseCase findTicketByIdGateway) {
        this.provideFeedbackGateway = provideFeedbackGateway;
        this.findTicketByIdGateway = findTicketByIdGateway;
    }

    @Override
    public void provideFeedback(Long ticketId, Feedback feedback) throws FeedbackNotCreatedException {
        try {
            SupportTicket ticket = findTicketByIdGateway.findTicketById(ticketId);
            feedback.setSupportTicket(ticket);
            boolean success = provideFeedbackGateway.provideFeedback(feedback);
            if (!success) {
                throw new FeedbackNotCreatedException(ErrorCodeEnum.FD0003.getCode(), ErrorCodeEnum.FD0003.getMessage());
            }

        } catch (Exception ex) {
            log.info("Error providing feedback with ID: {}. Error details: {}", ticketId, ex.getMessage());
            throw new FeedbackNotCreatedException(ErrorCodeEnum.FD0003.getCode(),
                    ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.FD0003));
        }
    }

}
