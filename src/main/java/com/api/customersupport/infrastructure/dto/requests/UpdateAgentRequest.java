package com.api.customersupport.infrastructure.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record UpdateAgentRequest(@NotNull UUID id, @NotBlank String name, @NotBlank String email, @NotBlank String phone
        , @NotBlank String password) {
}
