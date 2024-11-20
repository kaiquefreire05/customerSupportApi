package com.api.customersupport.infrastructure.entities;

import com.api.customersupport.domain.enums.CategoryEnum;
import com.api.customersupport.domain.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supporttickets")
public class SupportTicketEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "Category", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(name = "Closed_At")
    private LocalDateTime closedAt;

    @Column(name = "Created_At", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "Updated_At")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Client_Id", referencedColumnName = "Id", nullable = false)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Agent_Id")
    private AgentEntity assignedAgent;

    @OneToOne(mappedBy = "supportTicket")
    private FeedbackEntity feedback;

    // Constructor
    public SupportTicketEntity(String title, String description, StatusEnum status, CategoryEnum category
            , LocalDateTime closedAt, LocalDateTime createdAt, LocalDateTime updatedAt, ClientEntity client
    ) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.closedAt = closedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.client = client;

    }
}
