package com.api.customersupport.domain.models;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.api.customersupport.domain.validators.EmailValidator.isValidEmail;
import static com.api.customersupport.domain.validators.PhoneValidator.isValidPhone;

public class Agent {
    // Attributes
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<SupportTicket> assignedTickets;

    // Constructors
    public Agent(UUID id, String name, String email, String phone, String password, LocalDateTime createdAt
            , LocalDateTime updatedAt, List<SupportTicket> assignedTickets) throws EmailInvalidException, PhoneInvalidException {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPhone(phone);
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.assignedTickets = assignedTickets;
    }

    public Agent(String name, String email, String phone, String password, LocalDateTime createdAt
            , LocalDateTime updatedAt, List<SupportTicket> assignedTickets) throws EmailInvalidException, PhoneInvalidException {
        this.name = name;
        setEmail(email);
        setPhone(phone);
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.assignedTickets = assignedTickets;
    }

    public Agent(UUID id, String name, String email, String phone, String password, LocalDateTime createdAt
            , LocalDateTime updatedAt) throws EmailInvalidException, PhoneInvalidException {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPhone(phone);
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Agent() {
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailInvalidException {
        if (!isValidEmail(email)) {
            throw new EmailInvalidException(ErrorCodeEnum.ON0001.getCode(), ErrorCodeEnum.ON0001.getMessage());

        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws PhoneInvalidException {
        if (!isValidPhone(phone)) {
            throw new PhoneInvalidException(ErrorCodeEnum.ON0002.getCode(), ErrorCodeEnum.ON0002.getMessage());
        }
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<SupportTicket> getAssignedTickets() {
        return assignedTickets;
    }

    public void setAssignedTickets(List<SupportTicket> assignedTickets) {
        this.assignedTickets = assignedTickets;
    }

    // HashCode and Equals

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agent agent)) return false;

        return Objects.equals(id, agent.id) && Objects.equals(name, agent.name) && Objects.equals(email, agent.email)
                && Objects.equals(phone, agent.phone) && Objects.equals(password, agent.password)
                && Objects.equals(createdAt, agent.createdAt) && Objects.equals(updatedAt, agent.updatedAt)
                && Objects.equals(assignedTickets, agent.assignedTickets);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(phone);
        result = 31 * result + Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        result = 31 * result + Objects.hashCode(assignedTickets);
        return result;
    }
}
