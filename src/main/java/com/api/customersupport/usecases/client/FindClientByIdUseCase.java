package com.api.customersupport.usecases.client;

import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;

import java.util.UUID;

public interface FindClientByIdUseCase {
    Client findClientById(UUID clientId) throws ClientNotFoundException;
}
