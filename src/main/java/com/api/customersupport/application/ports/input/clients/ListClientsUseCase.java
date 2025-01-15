package com.api.customersupport.application.ports.input.clients;

import com.api.customersupport.domain.models.Client;

import java.util.List;

public interface ListClientsUseCase {

    List<Client> listClients();
}
