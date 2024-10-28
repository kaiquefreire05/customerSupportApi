package com.api.customersupport.infrastructure.mapper;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.MappingException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.dto.requests.CreateClientRequest;
import com.api.customersupport.infrastructure.entities.ClientEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    // Dependency Injection
    private final SupportTicketMapper supportTicketMapper;

    public ClientMapper(@Lazy SupportTicketMapper supportTicketMapper) {
        this.supportTicketMapper = supportTicketMapper;
    }

    // Methods
    public Client toDomainModel(ClientEntity clientEntity) throws MatchException {
        try {
            return new Client(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getEmail(),
                clientEntity.getPassword(),
                clientEntity.getPhone(),
                clientEntity.getAddress(),
                clientEntity.getCreatedAt(),
                clientEntity.getUpdatedAt(),
                supportTicketMapper.toDomainModelList(clientEntity.getSupportTickets())
            );
        } catch (EmailInvalidException | PhoneInvalidException ex) {
            throw new MappingException(ErrorCodeEnum.MP0001.getCode(),
                    ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.MP0001));
        }
    }

    public Client toDomainCreateRequest(CreateClientRequest request)
            throws EmailInvalidException, PhoneInvalidException {
        return new Client(
                request.name(),
                request.email(),
                request.password(),
                request.phone(),
                request.address(),
                LocalDateTime.now(),
                null,
                new ArrayList<>()
        );
    }

    public ClientEntity toEntity(Client client) {
        return new ClientEntity(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPassword(),
                client.getPhone(),
                client.getAddress(),
                client.getCreatedAt(),
                client.getUpdatedAt(),
                supportTicketMapper.toEntityList(client.getSupportTickets())
        );
    }

    public ClientEntity toEntityUpdate(Client client) {
        return new ClientEntity(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPassword(),
                client.getPhone(),
                client.getAddress(),
                client.getCreatedAt(),
                client.getUpdatedAt(),
                supportTicketMapper.toEntityList(client.getSupportTickets())
        );
    }

    public List<Client> toDomainModelList(List<ClientEntity> clientEntities) {
        return clientEntities.stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }
}
