package com.api.customersupport.application.gateway.client;

import java.util.UUID;

public interface DeleteClientGateway {
    boolean deleteClient(UUID clientId);
}
