package com.api.customersupport.infrastructure.controllers;

import com.api.customersupport.application.ports.input.clients.FindClientByIdUseCase;
import com.api.customersupport.application.ports.input.ticket.*;
import com.api.customersupport.domain.enums.StatusEnum;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.dto.requests.ticket.CreateTicketRequest;
import com.api.customersupport.infrastructure.dto.requests.ticket.UpdateTicketRequest;
import com.api.customersupport.infrastructure.dto.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@RestController
@RequestMapping("api/v1/ticket")
@CrossOrigin(origins = "http://localhost:3000")
public class SupportTicketController {

    private final AssignAgentToTicketUseCase assignAgentToTicketUseCase;
    private final CloseSupportTicketUseCase closeSupportTicketUseCase;
    private final CreateSupportTicketUseCase createSupportTicketUseCase;
    private final FindTicketByIdUseCase findTicketByIdUseCase;
    private final ListSupportTicketsUseCase listSupportTicketsUseCase;
    private final UpdateSupportTicketUseCase updateSupportTicketUseCase;
    private final FindClientByIdUseCase findClientByIdUseCase;
    private final DeleteSupportTicketUseCase deleteSupportTicketUseCase;
    private final ListOpenSupportTicketsUseCase listOpenSupportTicketsUseCase;
    private final GetTicketsByAgentIdUseCase getTicketsByAgentIdUseCase;
    private final ListOpenTicketsWithoutAgentUseCase listOpenTicketsWithoutAgentUseCase;

    public SupportTicketController(AssignAgentToTicketUseCase assignAgentToTicketUseCase,
                                   CloseSupportTicketUseCase closeSupportTicketUseCase,
                                   CreateSupportTicketUseCase createSupportTicketUseCase,
                                   FindTicketByIdUseCase findTicketByIdUseCase,
                                   ListSupportTicketsUseCase listSupportTicketsUseCase,
                                   UpdateSupportTicketUseCase updateSupportTicketUseCase,
                                   FindClientByIdUseCase findClientByIdUseCase,
                                   DeleteSupportTicketUseCase deleteSupportTicketUseCase,
                                   ListOpenSupportTicketsUseCase listOpenSupportTicketsUseCase,
                                   GetTicketsByAgentIdUseCase getTicketsByAgentIdUseCase,
                                   ListOpenTicketsWithoutAgentUseCase listOpenTicketsWithoutAgentUseCase) {
        this.assignAgentToTicketUseCase = assignAgentToTicketUseCase;
        this.closeSupportTicketUseCase = closeSupportTicketUseCase;
        this.createSupportTicketUseCase = createSupportTicketUseCase;
        this.findTicketByIdUseCase = findTicketByIdUseCase;
        this.listSupportTicketsUseCase = listSupportTicketsUseCase;
        this.updateSupportTicketUseCase = updateSupportTicketUseCase;
        this.findClientByIdUseCase = findClientByIdUseCase;
        this.deleteSupportTicketUseCase = deleteSupportTicketUseCase;
        this.listOpenSupportTicketsUseCase = listOpenSupportTicketsUseCase;
        this.getTicketsByAgentIdUseCase = getTicketsByAgentIdUseCase;
        this.listOpenTicketsWithoutAgentUseCase = listOpenTicketsWithoutAgentUseCase;
    }


    // Controller Methods
    @GetMapping("/all")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<List<SupportTicket>>> getAll() {
        log.info("Request received to get all entities support ticket::SupportTicketController");
        try {
            List<SupportTicket> supportTickets = listSupportTicketsUseCase.listSupportTickets();
            return ResponseEntity.ok().body(BaseResponse.<List<SupportTicket>>builder().success(true)
                    .message("All support tickets successfully returned.").result(supportTickets).build());

        } catch (Exception ex) {
            log.error("Error occurred while trying to get all support tickets. Error details: {}::SupportTicketController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<List<SupportTicket>>builder().success(false)
                    .result(null).message("Error while trying to get all support tickets").build());
        }
    }

    @GetMapping("/open")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<List<SupportTicket>>> getAllOpen() {
        log.info("Request received to get all open support tickets::SupportTicketController");
        try {
            List<SupportTicket> openTickets = listOpenSupportTicketsUseCase.listOpenSupportTickets();
            return ResponseEntity.ok().body(BaseResponse.<List<SupportTicket>>builder().success(true)
                    .message("All open support tickets successfully returned.").result(openTickets).build());
        } catch (Exception ex) {
            log.error("Error occurred while trying to get all open support tickets. Error details: {}::SupportTicketController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<List<SupportTicket>>builder().success(false)
                    .result(null).message("Error while trying to get all open support tickets").build());
        }
    }

    @GetMapping("/open_without_agent")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<List<SupportTicket>>> getAllOpenWithoutAgent() {
        log.info("Request received to get all open tickets without agent::SupportTicketController");
        try {
            List<SupportTicket> openTicketsWithoutAgent = listOpenTicketsWithoutAgentUseCase.listOpenTicketsWithoutAgent();
            return ResponseEntity.ok().body(BaseResponse.<List<SupportTicket>>builder().success(true).result(openTicketsWithoutAgent)
                    .message("All open tickets without agent successfully returned").build());
        } catch (Exception ex) {
            log.error("Error occurred while trying to get all open tickets without agent. Error details: {}::SupportTicketController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<List<SupportTicket>>builder()
                    .success(false).result(null).message("Error while trying to get all open tickets without agent").build());
        }
    }

    @GetMapping("id/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<SupportTicket>> getById(@PathVariable Long id) {
        log.info("Request received to get a entity by id::AgentController");
        try {
            log.info("Starting to get a entity by id::AgentController");
            SupportTicket supportTicket = findTicketByIdUseCase.findTicketById(id);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<SupportTicket>builder().success(true)
                    .result(supportTicket).message("Support Ticket with id " + id + " successfully returned").build());
        } catch (Exception ex) {
            log.error("Error occurred while trying to get support ticket by id. Error details: {}::SupportTicket", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<SupportTicket>builder().success(false)
                    .result(null).message("Error while trying to get support ticket by id").build());
        }
    }

    @GetMapping("/agent/{assignedAgentId}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<List<SupportTicket>>> getTicketsByAgentId (@PathVariable UUID assignedAgentId) {
        log.info("Request received to get all tickets by agent::SupportTicketController");
        try {
            log.info("'Starting to get all tickets by agent::SupportTicketController");
            List<SupportTicket> supportTickets = getTicketsByAgentIdUseCase.getTicketsByAgentId(assignedAgentId);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<List<SupportTicket>>builder().success(true)
                    .result(supportTickets).message("All tickets by agent successfully returned").build());
        } catch (Exception ex) {
            log.error("Error occurred while trying to get all tickets by agent. Error details: {}::SupportTicketController",
                    ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<List<SupportTicket>>builder().success(false)
                    .result(null).message("Error while trying to get all tickets by agent").build());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id) {
        log.info("Request received to delete a support ticket::SupportTicketController");
        try {
            deleteSupportTicketUseCase.deleteSupportTicket(id);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Boolean>builder().success(true)
                    .message("Support ticket deleted successfully.").build());
        } catch (Exception ex) {
            log.error("Error occurred while trying to delete a support ticket. Error details: {}::SupportTicketController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Boolean>builder().success(false)
                    .result(false).message("Error while trying to delete a support ticket").build());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createSupportTicket(@Valid @RequestBody CreateTicketRequest request) {
        log.info("Request received to create support ticket::SupportTicketController");
        try {
            Client client = findClientByIdUseCase.findClientById(request.clientId());
            SupportTicket ticket = new SupportTicket(
                    request.title(),
                    request.description(),
                    StatusEnum.OPEN,
                    request.category(),
                    null,
                    LocalDateTime.now(),
                    null,
                    client,
                    null,
                    null
            );
            createSupportTicketUseCase.createSupportTicket(ticket);
            log.info("Support ticket created successfully::SupportTicketController");
            return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.<String>builder().success(true)
                    .message("Support ticket created successfully.").build());
        } catch (Exception ex) {
            log.error("Error occurred while creating a support ticket. Error details: {}::SupportTicketController"
                    , ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder()
                    .result(null).success(false).message("Error occurred while creating a support ticket.").build());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<String>> updateSupportTicket(@Valid @RequestBody UpdateTicketRequest request) {
        log.info("Request received to update a support ticket::SupportTicketController");
        try {
            log.info("Updating values of support ticket::SupportTicketController");
            SupportTicket foundTicket = findTicketByIdUseCase.findTicketById(request.id());
            foundTicket.setTitle(request.title());
            foundTicket.setDescription(request.description());
            foundTicket.setStatus(request.status());
            foundTicket.setCategory(request.category());

            updateSupportTicketUseCase.updateSupportTicket(foundTicket);
            log.info("Support ticket updated successfully::SupportTicketController");
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<String>builder().success(true)
                    .message("Support ticket updated successfully.").build());

        } catch (Exception ex) {
            log.info("Error occurred while updating a support ticker. Error details: {}::SupportTicketController"
                    , ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder()
                    .result(null).success(false).message("Error occurred while updating a support ticket.").build());
        }
    }

    @PutMapping("/assign-agent")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<String>> assignAgentToTicket(@RequestParam Long ticketId, @RequestParam UUID agentId) {
        log.info("Request received to assign agent to a support ticket::SupportTicketController");
        try {
            assignAgentToTicketUseCase.assignAgentToTicket(ticketId, agentId);
            log.info("Agent assigned to support ticket successfully::SupportTicketController");
            return ResponseEntity.ok().body(BaseResponse.<String>builder().success(true)
                    .message("Agent assigned to support ticket successfully.").build());
        } catch (Exception ex) {
            log.error("Error occurred while assigning agent to support ticket. Error details: {}::SupportTicketController"
                    , ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder()
                    .result(null).success(false).message("Error occurred while assigning agent to support ticket.").build());
        }
    }

    @PutMapping("/close_ticket/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponse<String>> closeSupportTicket(@PathVariable Long id) {
        log.info("Request received to close a support ticket::SupportTicketController");
        try {
            closeSupportTicketUseCase.closeSupportTicket(id);
            log.info("Support ticket closed successfully::SupportTicketController");
            return ResponseEntity.ok().body(BaseResponse.<String>builder().success(true)
                    .message("Support ticket closed successfully.").build());
        } catch (Exception ex) {
            log.error("Error occurred while closing a support ticket. Error details: {}::SupportTicketController"
                    , ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder()
                    .result(null).success(false).message("Error occurred while closing a support ticket.").build());
        }
    }
}
