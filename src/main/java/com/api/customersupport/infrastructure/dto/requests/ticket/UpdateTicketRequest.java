package com.api.customersupport.infrastructure.dto.requests.ticket;

import com.api.customersupport.domain.enums.CategoryEnum;
import com.api.customersupport.domain.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketRequest(@NotNull Long id, @NotBlank String title, @NotBlank String description
        , @NotNull StatusEnum status, @NotNull CategoryEnum category) {
}
