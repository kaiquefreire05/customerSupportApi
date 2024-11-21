package com.api.customersupport.usecases.client;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.models.Client;

public interface CreateClientUseCase {
    Client createClient(Client client) throws InternalServerErrorException;
}
