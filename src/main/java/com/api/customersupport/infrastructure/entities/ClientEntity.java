package com.api.customersupport.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @Column(name = "Id", nullable = false, columnDefinition = "CHAR(36)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Created_At", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "Updated_At", nullable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupportTicketEntity> supportTickets;

    // Constructor
    public ClientEntity(String name, String email, String phone, String address
            , LocalDateTime createdAt, LocalDateTime updatedAt, List<SupportTicketEntity> supportTickets) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.supportTickets = supportTickets;
    }
}
