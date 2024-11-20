package com.api.customersupport.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agents")
public class AgentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "Id", nullable = false, columnDefinition = "UUID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Created_At", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "Updated_At", nullable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "assignedAgent")
    private List<SupportTicketEntity> assignedTickets;

    // Constructor
    public AgentEntity(String name, String email, String phone, String password, LocalDateTime createdAt
            , LocalDateTime updatedAt, List<SupportTicketEntity> assignedTickets) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.assignedTickets = assignedTickets;
    }

    public AgentEntity(UUID id, String name, String email, String phone, String password, LocalDateTime createdAt
            , LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
