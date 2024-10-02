package com.api.customersupport.domain.models;

import com.api.customersupport.domain.enums.CategoryEnum;
import com.api.customersupport.domain.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class SupportTicket {
    // Attributes
    private Long id;
    private String title;
    private String description;
    private StatusEnum status;
    private CategoryEnum category;
    private LocalDateTime closedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Client client;
    private Agent assignedAgent;
    private Feedback feedback;

    // Constructors
    public SupportTicket(Long id, String title, String description, StatusEnum status, CategoryEnum category
            , LocalDateTime closedAt, LocalDateTime createdAt, LocalDateTime updatedAt, Client client
            , Agent assignedAgent, Feedback feedback) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.closedAt = closedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.client = client;
        this.assignedAgent = assignedAgent;
        this.feedback = feedback;
    }

    public SupportTicket(String title, String description, StatusEnum status, CategoryEnum category
            , LocalDateTime closedAt, LocalDateTime createdAt, LocalDateTime updatedAt, Client client
            , Agent assignedAgent, Feedback feedback) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.closedAt = closedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.client = client;
        this.assignedAgent = assignedAgent;
        this.feedback = feedback;
    }

    public SupportTicket() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agent getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    // HashCode and Equals
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupportTicket that)) return false;

        return Objects.equals(id, that.id) && Objects.equals(title, that.title)
                && Objects.equals(description, that.description) && status == that.status && category == that.category
                && Objects.equals(closedAt, that.closedAt) && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(client, that.client)
                && Objects.equals(assignedAgent, that.assignedAgent) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(status);
        result = 31 * result + Objects.hashCode(category);
        result = 31 * result + Objects.hashCode(closedAt);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        result = 31 * result + Objects.hashCode(client);
        result = 31 * result + Objects.hashCode(assignedAgent);
        result = 31 * result + Objects.hashCode(feedback);
        return result;
    }
}
