package com.api.customersupport.application.ports.input.clients;

import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Client;

public interface UpdateClientUseCase {
    Client updateClient(Client client) throws ClientNotFoundException, EmailUnavailableException, EmailInvalidException, PhoneInvalidException;
}
