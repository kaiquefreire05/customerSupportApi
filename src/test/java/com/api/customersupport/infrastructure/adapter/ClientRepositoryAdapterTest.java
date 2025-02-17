package com.api.customersupport.infrastructure.adapter;

import com.api.customersupport.application.mapper.ClientMapper;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.adapters.ClientRepositoryAdapter;
import com.api.customersupport.infrastructure.entities.ClientEntity;
import com.api.customersupport.infrastructure.jpa.ClientEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientRepositoryAdapterTest {
    @Mock
    private ClientEntityRepository repository;
    @Mock
    private ClientMapper mapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClientRepositoryAdapter clientRepositoryAdapter;

    private Client client;
    private ClientEntity clientEntity;
    private UUID clientId;

    @BeforeEach
    void setUp() throws EmailInvalidException, PhoneInvalidException {
        clientId = UUID.randomUUID();
        client = new Client(clientId, "Maria Tereza", "maria@example.com", "password123",
                "(16) 95447-4522", "Rua das Flores, 123", LocalDateTime.now(), null);
        clientEntity = new ClientEntity(clientId, "Maria Tereza", "maria@example.com", "password123",
                "(16) 95447-4522", "Rua das Flores, 123", LocalDateTime.now(), null);
    }

    @Test
    void testSaveClient() {
        when(passwordEncoder.encode(client.getPassword())).thenReturn("encodedPassword");
        when(mapper.toEntity(client)).thenReturn(clientEntity);
        when(repository.save(clientEntity)).thenReturn(clientEntity);
        when(mapper.toDomainModel(clientEntity)).thenReturn(client);

        Client savedClient = clientRepositoryAdapter.createClient(client);

        // Assertations
        assertNotNull(savedClient);
        assertEquals(client.getEmail(), savedClient.getEmail());
        verify(repository, times(1)).save(clientEntity);
    }

    @Test
    void testUpdateClientSuccess() throws ClientNotFoundException {
        when(repository.findById(clientId)).thenReturn(Optional.of(clientEntity));
        when(mapper.toEntityUpdate(client)).thenReturn(clientEntity);
        when(clientEntity.getPassword()).thenReturn("encodedPassword");
        when(repository.save(clientEntity)).thenReturn(clientEntity);
        when(mapper.toDomainModel(clientEntity)).thenReturn(client);

        Client updatedClient = clientRepositoryAdapter.updateClient(client);

        // Assertations
        assertNotNull(updatedClient);
        assertEquals(client.getEmail(), updatedClient.getEmail());
        verify(repository, times(1)).save(clientEntity);
    }
}
