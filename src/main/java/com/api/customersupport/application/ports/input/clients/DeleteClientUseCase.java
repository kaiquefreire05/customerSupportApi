package com.api.customersupport.application.ports.input.clients;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

import java.util.UUID;

public interface DeleteClientUseCase {
    void deleteClient(UUID clientId) throws InternalServerErrorException;
}
