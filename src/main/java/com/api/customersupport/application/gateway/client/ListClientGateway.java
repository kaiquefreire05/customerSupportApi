package com.api.customersupport.application.gateway.client;

import com.api.customersupport.domain.models.Client;

import java.util.List;

public interface ListClientGateway {
    List<Client> listClients();
}
