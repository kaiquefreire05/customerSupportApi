package com.api.customersupport.application.gateway.feedback;

import com.api.customersupport.domain.models.Feedback;

public interface ProvideFeedbackGateway {

    boolean provideFeedback(Feedback feedback);

}
