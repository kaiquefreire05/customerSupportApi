package com.api.customersupport.application.gateway.client;

import com.api.customersupport.domain.models.Client;

public interface CreateClientGateway {
    Boolean createClient(Client client);
}
