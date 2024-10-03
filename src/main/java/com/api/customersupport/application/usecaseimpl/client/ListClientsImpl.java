package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.ListClientGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.usecases.client.ListClientsUseCase;

import java.util.List;

public class ListClientsImpl implements ListClientsUseCase {
    // Dependency Injection
    private final ListClientGateway listClientGateway;

    public ListClientsImpl(ListClientGateway listClientGateway) {
        this.listClientGateway = listClientGateway;
    }

    @Override
    public List<Client> listClients() {
        return listClientGateway.listClients();
    }
}
