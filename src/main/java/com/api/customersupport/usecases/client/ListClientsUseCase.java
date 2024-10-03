package com.api.customersupport.usecases.client;

import com.api.customersupport.domain.models.Client;

import java.util.List;

public interface ListClientsUseCase {

    List<Client> listClients();
}
