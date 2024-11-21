package com.api.customersupport.domain.models;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.RatingInvalidException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Feedback {
    // Attributes
    private Long id;
    private String comments;
    private Integer rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SupportTicket supportTicket;

    // Constructors
    public Feedback(Long id, String comments, Integer rating, LocalDateTime createdAt, LocalDateTime updatedAt
            , SupportTicket supportTicket) throws RatingInvalidException {
        this.id = id;
        this.comments = comments;
        setRating(rating);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.supportTicket = supportTicket;
    }

    public Feedback(String comments, Integer rating, LocalDateTime createdAt, LocalDateTime updatedAt
            , SupportTicket supportTicket) throws RatingInvalidException {
        this.comments = comments;
        setRating(rating);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.supportTicket = supportTicket;
    }

    public Feedback() {
    }

    // Methods
    public Boolean isRatingValid(Integer rating) {
        return rating != null && rating >= 1 && rating <= 5;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) throws RatingInvalidException {
        if (!isRatingValid(rating)) {
            throw new RatingInvalidException(ErrorCodeEnum.FD0001.getCode(), ErrorCodeEnum.FD0001.getMessage());
        }
        this.rating = rating;
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

    public SupportTicket getSupportTicket() {
        return supportTicket;
    }

    public void setSupportTicket(SupportTicket supportTicket) {
        this.supportTicket = supportTicket;
    }

    // HashCode and Equals

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Feedback feedback)) return false;

        return Objects.equals(id, feedback.id) && Objects.equals(comments, feedback.comments) && Objects.equals(rating
                , feedback.rating) && Objects.equals(createdAt, feedback.createdAt) && Objects.equals(updatedAt
                , feedback.updatedAt) && Objects.equals(supportTicket, feedback.supportTicket);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(comments);
        result = 31 * result + Objects.hashCode(rating);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        result = 31 * result + Objects.hashCode(supportTicket);
        return result;
    }
}
