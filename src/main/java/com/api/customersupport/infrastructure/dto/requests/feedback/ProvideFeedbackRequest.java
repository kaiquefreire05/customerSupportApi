package com.api.customersupport.infrastructure.dto.requests.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProvideFeedbackRequest(@NotBlank String comments, @NotBlank int rating, @NotNull Long supportTicketId) {

}
