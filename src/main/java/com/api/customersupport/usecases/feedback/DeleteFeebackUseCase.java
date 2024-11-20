package com.api.customersupport.usecases.feedback;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

public interface DeleteFeebackUseCase {

    void deleteFeedback(Long feedbackId) throws InternalServerErrorException;

}
