package com.api.customersupport.application.services.feedback;

import com.api.customersupport.application.ports.input.feedback.DeleteFeebackUseCase;
import com.api.customersupport.application.ports.output.FeedbackRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteFeedbackService implements DeleteFeebackUseCase {
    //Dependency injection
    private final FeedbackRepositoryPort feedbackRepositoryPort;

    public DeleteFeedbackService(FeedbackRepositoryPort feedbackRepositoryPort) {
        this.feedbackRepositoryPort = feedbackRepositoryPort;
    }

    @Override
    public void deleteFeedback(Long feedbackId) throws InternalServerErrorException {
        log.info("Starting of feedback deletion::DeleteFeedbackImpl");
        try {
            boolean success = feedbackRepositoryPort.deleteFeedback(feedbackId);
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
