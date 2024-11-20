package com.api.customersupport.infrastructure.dto.requests.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateFeedbackRequest(@NotNull Long id, @NotBlank String comments, @NotBlank int rating) {

}
