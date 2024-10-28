package com.api.customersupport.application.gateway.client;

import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;

public interface FindClientByEmailGateway {
    Client findClientByEmail(String email) throws ClientNotFoundException;
}
