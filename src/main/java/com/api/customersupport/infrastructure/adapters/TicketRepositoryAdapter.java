package com.api.customersupport.infrastructure.adapters;

import com.api.customersupport.application.mapper.SupportTicketMapper;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.enums.StatusEnum;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.AgentEntity;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import com.api.customersupport.infrastructure.jpa.AgentEntityRepository;
import com.api.customersupport.infrastructure.jpa.SupportTicketEntityRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Repository
public class TicketRepositoryAdapter implements TicketRepositoryPort {
    // Dependency Injection
    private final SupportTicketEntityRepository ticketRepository;
    private final AgentEntityRepository agentRepository;
    private final SupportTicketMapper ticketMapper;

    public TicketRepositoryAdapter(SupportTicketEntityRepository ticketRepository, AgentEntityRepository agentRepository,
                                   SupportTicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.agentRepository = agentRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public Boolean assignTicketToAgent(Long ticketId, UUID agentId) {
        log.info("Assigning ticket with id: {} to agent with id: {}::TicketRepositoryAdapter", ticketId, agentId);
        try {
            // Finding ticket and agent
            SupportTicketEntity ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> {
                        log.warn("Ticket with id {} not found::TicketRepositoryAdapter", ticketId);
                        return new TicketSupportNotFoundException(ErrorCodeEnum.TS0002.getCode(),
                                ErrorCodeEnum.TS0002.getMessage());
                    });

            AgentEntity agent = agentRepository.findById(agentId)
                    .orElseThrow(() -> {
                        log.warn("Agent with id {} not found::TicketRepositoryAdapter", agentId);
                        return new AgentNotFoundException(ErrorCodeEnum.ON0006.getCode(),
                                ErrorCodeEnum.ON0006.getMessage());
                    });

            ticket.setAssignedAgent(agent);
            ticketRepository.save(ticket);

            log.info("Ticket {} successfully assigned to agent {}::TicketRepositoryAdapter", ticketId, agentId);
            return true;
        } catch (Exception ex) {
            log.error("Error assigning ticket {} to agent {}: {}::TicketRepositoryAdapter", ticketId, agentId, ex.getMessage(), ex);
            return false;
        }
    }


    @Override
    public Boolean closeTicket(Long ticketId) {
        log.info("Closing ticket with id: {}::TicketRepositoryAdapter", ticketId);
        try {
            // Finding ticket
            SupportTicketEntity ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> {
                        log.warn("Ticket with id {} not found::TicketRepositoryAdapter", ticketId);
                        return new TicketSupportNotFoundException(ErrorCodeEnum.TS0002.getCode(),
                                ErrorCodeEnum.TS0002.getMessage());
                    });

            // Verifying if ticket is already closed
            if (ticket.getStatus() == StatusEnum.CLOSED) {
                log.warn("Ticket with id {} is already closed::TicketRepositoryAdapter", ticketId);
                return false;
            }

            // Updating ticket status
            ticket.setStatus(StatusEnum.CLOSED);
            ticketRepository.save(ticket);

            log.info("Ticket {} successfully closed", ticketId);
            return true;
        } catch (Exception ex) {
            log.error("Error closing ticket {}: {}::TicketRepositoryAdapter", ticketId, ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public SupportTicket createTicket(SupportTicket ticket) {
        try {
            log.info("Creating ticket::TicketRepositoryAdapter");
            // Mapping ticket to entity and saving
            SupportTicketEntity ticketEntity = ticketMapper.toEntity(ticket);
            SupportTicketEntity savedTicket = ticketRepository.save(ticketEntity);

            log.info("Ticket created successfully::TicketRepositoryAdapter");
            return ticketMapper.toDomainModel(savedTicket);
        } catch (Exception ex) {
            log.error("Error creating ticket: {}", ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public Boolean deleteTicket(Long ticketId) {
        try {
            log.info("Deleting ticket with id: {}::TicketRepositoryAdapter", ticketId);
            ticketRepository.deleteById(ticketId);
            log.info("Ticket with id: {} deleted successfully::TicketRepositoryAdapter", ticketId);
            return true;
        } catch (Exception ex) {
            log.error("Error deleting ticket {}: {}::TicketRepositoryAdapter", ticketId, ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public SupportTicket getTicketById(Long ticketId) throws TicketSupportNotFoundException {
        log.info("Finding ticket by id: {}::TicketRepositoryAdapter", ticketId);
        return ticketRepository.findById(ticketId).map(ticketMapper::toDomainModel)
                .orElseThrow(() -> new TicketSupportNotFoundException(ErrorCodeEnum.TS0002.getCode(),
                        ErrorCodeEnum.TS0002.getMessage()));
    }

    @Override
    public List<SupportTicket> listTicketsByAgent(UUID agentId) {
        return List.of();
    }

    @Override
    public List<SupportTicket> listOpenTickets() {
        List<SupportTicketEntity> tickets = ticketRepository.listOpen();
        return ticketMapper.toDomainModelList(tickets);
    }

    @Override
    public List<SupportTicket> listOpenTicketsWithoutAgent() {
        List<SupportTicketEntity> tickets = ticketRepository.listOpenTicketsWithoutAgent();
        return ticketMapper.toDomainModelList(tickets);
    }

    @Override
    public List<SupportTicket> listTickets() {
        List<SupportTicketEntity> tickets = ticketRepository.findAll();
        return ticketMapper.toDomainModelList(tickets);
    }

    @Override
    public SupportTicket updateTicket(SupportTicket ticket) {
        try {
            log.info("Starting of ticket update with id: {}::TicketRepositoryAdapter", ticket.getId());
            var existentTicket = ticketRepository.findById(ticket.getId())
                    .orElseThrow(() -> new TicketSupportNotFoundException(ErrorCodeEnum.TS0002.getCode(),
                            ErrorCodeEnum.TS0002.getMessage()));
            // Updating and saving ticket
            var updatedTicket = ticketMapper.toEntityUpdate(ticket);
            updatedTicket.setUpdatedAt(LocalDateTime.now());
            log.info("End of ticket update with id: {}::TicketRepositoryAdapter", ticket.getId());
            return ticketMapper.toDomainModel(ticketRepository.save(updatedTicket));

        } catch (Exception ex) {
            log.info("Error in ticket update. Error details: {}::TicketRepositoryAdapter", ex.getMessage());
            return null;
        }
    }
}
