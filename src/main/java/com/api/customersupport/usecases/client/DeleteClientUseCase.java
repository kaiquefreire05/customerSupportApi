package com.api.customersupport.usecases.client;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

import java.util.UUID;

public interface DeleteClientUseCase {
    void deleteClient(UUID clientId) throws InternalServerErrorException;
}
