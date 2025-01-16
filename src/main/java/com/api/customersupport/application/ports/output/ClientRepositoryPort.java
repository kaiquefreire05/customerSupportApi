package com.api.customersupport.application.ports.output;

import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;

import java.util.List;
import java.util.UUID;

public interface ClientRepositoryPort {
    Client createClient(Client client);
    Boolean deleteClient(UUID clientId);
    Client findClientByEmail(String email) throws ClientNotFoundException;
    Client getClientById(UUID id) throws ClientNotFoundException;
    List<Client> listClients();
    Client updateClient(Client client) throws ClientNotFoundException;
}
