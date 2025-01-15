package com.api.customersupport.application.ports.input.clients;

import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.models.Client;

public interface CreateClientUseCase {
    Client createClient(Client client) throws InternalServerErrorException, EmailUnavailableException;
}
