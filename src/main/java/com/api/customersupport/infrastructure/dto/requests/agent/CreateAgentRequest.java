package com.api.customersupport.infrastructure.dto.requests.agent;

import jakarta.validation.constraints.NotBlank;

public record CreateAgentRequest(@NotBlank String name, @NotBlank String email, @NotBlank String phone,
                                 @NotBlank String password) {
}
