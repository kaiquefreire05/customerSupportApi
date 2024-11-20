package com.api.customersupport.infrastructure.dto.requests;

import com.api.customersupport.domain.enums.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTicketRequest(@NotBlank String title, @NotBlank String description, @NotNull CategoryEnum category
        , @NotNull UUID clientId) {
}
