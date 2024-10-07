package com.api.customersupport.infrastructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Feedbacks")
public class FeedbackEntity {

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

}