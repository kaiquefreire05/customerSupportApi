package com.api.customersupport.application.ports.input.clients;

import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;

import java.util.UUID;

public interface FindClientByIdUseCase {
    Client findClientById(UUID clientId) throws ClientNotFoundException;
}
