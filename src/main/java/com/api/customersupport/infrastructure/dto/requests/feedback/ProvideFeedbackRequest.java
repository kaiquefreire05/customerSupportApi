package com.api.customersupport.infrastructure.dto.requests.feedback;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProvideFeedbackRequest(
        @NotBlank(message = "Comments cannot be empty")
        String comments,
        @NotNull(message = "Rating cannot be null")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating,
        @NotNull(message = "Support Ticket ID cannot be null")
        Long supportTicketId
) {}

