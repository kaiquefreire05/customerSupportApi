package com.api.customersupport.application.ports.input.feedback;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

public interface DeleteFeebackUseCase {

    void deleteFeedback(Long feedbackId) throws InternalServerErrorException;

}
