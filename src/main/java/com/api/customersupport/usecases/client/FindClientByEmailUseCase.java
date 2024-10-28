package com.api.customersupport.usecases.client;

import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;

public interface FindClientByEmailUseCase {
    Client findClientByEmail(String email) throws ClientNotFoundException;
}
