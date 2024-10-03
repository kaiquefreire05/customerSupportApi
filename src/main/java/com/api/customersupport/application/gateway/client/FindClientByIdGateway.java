package com.api.customersupport.application.gateway.client;

import com.api.customersupport.domain.models.Client;

import java.util.UUID;

public interface FindClientByIdGateway {
    Client findClientById(UUID clientId);
}
