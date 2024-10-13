package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.ListClientGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.MappingException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.usecases.client.ListClientsUseCase;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class ListClientsImpl implements ListClientsUseCase {
    // Dependency Injection
    private final ListClientGateway listClientGateway;
    private final ClientMapper clientMapper;

    public ListClientsImpl(ListClientGateway listClientGateway, ClientMapper clientMapper) {
        this.listClientGateway = listClientGateway;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<Client> listClients() {
        return listClientGateway.listClients();

    }

}
