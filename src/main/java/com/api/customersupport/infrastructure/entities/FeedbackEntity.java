package com.api.customersupport.infrastructure.entities;

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
@Table(name = "Feedbacks")
public class FeedbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Comments", nullable = false)
    private String comments;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "Created_At", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "Updated_At")
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "Support_Ticket_Id", nullable = false)
    private SupportTicketEntity supportTicket;

    // Constructor
    public FeedbackEntity(String comments, Integer rating, LocalDateTime createdAt, LocalDateTime updatedAt
            , SupportTicketEntity supportTicket) {
        this.comments = comments;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.supportTicket = supportTicket;
    }
}