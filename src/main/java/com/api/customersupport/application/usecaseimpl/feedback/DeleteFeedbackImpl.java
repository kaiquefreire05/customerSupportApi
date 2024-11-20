package com.api.customersupport.application.usecaseimpl.feedback;

import com.api.customersupport.application.gateway.feedback.DeleteFeedbackGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.usecases.feedback.DeleteFeebackUseCase;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteFeedbackImpl implements DeleteFeebackUseCase {

    //Dependency injection
    private final DeleteFeedbackGateway deleteFeedbackGateway;

    public DeleteFeedbackImpl(DeleteFeedbackGateway deleteFeedbackGateway) {
        this.deleteFeedbackGateway = deleteFeedbackGateway;
    }

    @Override
    public void deleteFeedback(Long feedbackId) throws InternalServerErrorException {
        log.info("Starting of feedback deletion::DeleteFeedbackImpl");
        try {
            boolean success = deleteFeedbackGateway.deleteFeedback(feedbackId);
            if (!success) {
                throw new InternalServerErrorException(ErrorCodeEnum.FD0002.getCode(), ErrorCodeEnum.FD0002.getMessage());
            }
        } catch (Exception ex) {
            log.info("Error deleting feedback with ID: {}. Error details: {}", feedbackId, ex.getMessage());
            throw new InternalServerErrorException(ErrorCodeEnum.FD0002.getCode(),
                    ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.FD0002));
        }
    }

}
