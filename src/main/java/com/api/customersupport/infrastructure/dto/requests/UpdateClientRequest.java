package com.api.customersupport.infrastructure.dto.requests;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateClientRequest(@NotBlank UUID id, @NotBlank String name, @NotBlank String email
        , @NotBlank String password, @NotBlank String phone, @NotBlank String address) {
}
