package com.api.customersupport.usecases.client;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.models.Client;

public interface CreateClientUseCase {
    void createClient(Client client) throws InternalServerErrorException;
}
