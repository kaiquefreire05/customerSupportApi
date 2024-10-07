package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.entities.ClientEntity;

public class ClientMapper {
    // Dependency Injection
    private final SupportTicketMapper supportTicketMapper;

    public ClientMapper(SupportTicketMapper supportTicketMapper) {
        this.supportTicketMapper = supportTicketMapper;
    }

    // Methods
    public Client toDomainModel(ClientEntity clientEntity) throws EmailInvalidException, PhoneInvalidException {
        return new Client(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getEmail(),
                clientEntity.getPhone(),
                clientEntity.getAddress(),
                clientEntity.getCreatedAt(),
                clientEntity.getUpdatedAt(),
                supportTicketMapper.toDomainModelList(clientEntity.getSupportTickets())
        );
    }
}
