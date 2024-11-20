package com.api.customersupport.infrastructure.dto.requests.client;

import jakarta.validation.constraints.NotBlank;

public record CreateClientRequest(@NotBlank String name, @NotBlank String email, @NotBlank String password,
                                  @NotBlank String phone, @NotBlank String address) {
}
