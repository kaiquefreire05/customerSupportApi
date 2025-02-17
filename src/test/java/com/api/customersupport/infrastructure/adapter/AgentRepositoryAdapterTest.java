package com.api.customersupport.infrastructure.adapter;

import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.adapters.AgentRepositoryAdapter;
import com.api.customersupport.infrastructure.entities.AgentEntity;
import com.api.customersupport.infrastructure.jpa.AgentEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgentRepositoryAdapterTest {
    @Mock
    private AgentEntityRepository repository;
    @Mock
    private AgentMapper mapper;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private AgentRepositoryAdapter agentRepositoryAdapter;

    private Agent agent;
    private AgentEntity agentEntity;
    private UUID agentId;

    @BeforeEach
    void setUp() throws EmailInvalidException, PhoneInvalidException {
        agentId = UUID.randomUUID();
        agent = new Agent(agentId, "John Doe", "john.doe@example.com", "(16) 98832-2434",
                "password123", LocalDateTime.now(), null);
        agentEntity = new AgentEntity(agentId, "John Doe", "john.doe@example.com", "16988322434",
                "password123", LocalDateTime.now(), null);
    }

    @Test
    void testSaveAgent() {
        when(encoder.encode(agent.getPassword())).thenReturn("encodedPassword");
        when(mapper.toEntity(agent)).thenReturn(agentEntity);
        when(repository.save(agentEntity)).thenReturn(agentEntity);
        when(mapper.toDomainModel(agentEntity)).thenReturn(agent);

        Agent savedAgent = agentRepositoryAdapter.saveAgent(agent);

        // Assertions
        assertNotNull(savedAgent);
        assertEquals(agent.getEmail(), savedAgent.getEmail());
        verify(repository, times(1)).save(agentEntity);
    }

    @Test
    void testUpdateAgent_Success() throws AgentNotFoundException {
        when(repository.findById(agentId)).thenReturn(Optional.of(agentEntity));
        when(mapper.toEntityUpdate(agent)).thenReturn(agentEntity);
        when(repository.save(agentEntity)).thenReturn(agentEntity);
        when(mapper.toDomainModel(agentEntity)).thenReturn(agent);

        Agent updatedAgent = agentRepositoryAdapter.updateAgent(agent);

        assertNotNull(updatedAgent);
        assertEquals(agent.getEmail(), updatedAgent.getEmail());
        verify(repository, times(1)).save(agentEntity);
    }

    @Test
    void testUpdateAgent_NotFound() {
        when(repository.findById(agentId)).thenReturn(Optional.empty());

        AgentNotFoundException thrown = assertThrows(AgentNotFoundException.class,
                () -> agentRepositoryAdapter.updateAgent(agent));

        assertEquals(ErrorCodeEnum.ON0006.getMessage(), thrown.getCode());
        verify(repository, never()).save(any());
    }

    @Test
    void testGetAgentById_Success() throws AgentNotFoundException {
        when(repository.findById(agentId)).thenReturn(Optional.of(agentEntity));
        when(mapper.toDomainModel(agentEntity)).thenReturn(agent);

        Agent foundAgent = agentRepositoryAdapter.getAgentById(agentId);

        assertNotNull(foundAgent);
        assertEquals(agent.getEmail(), foundAgent.getEmail());
        verify(repository, times(1)).findById(agentId);
    }

    @Test
    void testGetAgentById_NotFound() {
        when(repository.findById(agentId)).thenReturn(Optional.empty());

        assertThrows(AgentNotFoundException.class, () -> agentRepositoryAdapter.getAgentById(agentId));
        verify(repository, times(1)).findById(agentId);
    }

    @Test
    void testGetAgentByEmail_Success() throws AgentNotFoundException {
        when(repository.findByEmail(agent.getEmail())).thenReturn(Optional.of(agentEntity));
        when(mapper.toDomainModel(agentEntity)).thenReturn(agent);

        Agent foundAgent = agentRepositoryAdapter.getAgentByEmail(agent.getEmail());

        assertNotNull(foundAgent);
        assertEquals(agent.getEmail(), foundAgent.getEmail());
        verify(repository, times(1)).findByEmail(agent.getEmail());
    }

    @Test
    void testGetAgentByEmail_NotFound() {
        when(repository.findByEmail(agent.getEmail())).thenReturn(Optional.empty());

        assertThrows(AgentNotFoundException.class, () -> agentRepositoryAdapter.getAgentByEmail(agent.getEmail()));
        verify(repository, times(1)).findByEmail(agent.getEmail());
    }

    @Test
    void testListAgents() {
        when(repository.findAll()).thenReturn(List.of(agentEntity));
        when(mapper.toDomainModelList(List.of(agentEntity))).thenReturn(List.of(agent));

        List<Agent> agents = agentRepositoryAdapter.listAgents();

        assertFalse(agents.isEmpty());
        assertEquals(1, agents.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testDeleteAgent_Success() {
        doNothing().when(repository).deleteById(agentId);

        Boolean result = agentRepositoryAdapter.deleteAgent(agentId);

        assertTrue(result);
        verify(repository, times(1)).deleteById(agentId);
    }

    @Test
    void testDeleteAgent_Failure() {
        doThrow(new IllegalArgumentException("Invalid ID")).when(repository).deleteById(agentId);

        Boolean result = agentRepositoryAdapter.deleteAgent(agentId);

        assertFalse(result);
        verify(repository, times(1)).deleteById(agentId);
    }
}
