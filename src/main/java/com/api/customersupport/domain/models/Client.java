package com.api.customersupport.domain.models;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.api.customersupport.domain.validators.EmailValidator.isValidEmail;
import static com.api.customersupport.domain.validators.PhoneValidator.isValidPhone;

public class Client {
    // Attributes
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<SupportTicket> supportTickets;

    // Constructors
    public Client(UUID id, String name, String email, String phone, String address, LocalDateTime createdAt
            , LocalDateTime updatedAt, List<SupportTicket> supportTickets) throws EmailInvalidException, PhoneInvalidException {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPhone(phone);
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.supportTickets = supportTickets;
    }

    public Client(String name, String email, String phone, String address, LocalDateTime createdAt
            , LocalDateTime updatedAt, List<SupportTicket> supportTickets) throws EmailInvalidException, PhoneInvalidException {
        this.name = name;
        setEmail(email);
        setPhone(phone);
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.supportTickets = supportTickets;
    }

    public Client() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<SupportTicket> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicket> supportTickets) {
        this.supportTickets = supportTickets;
    }

    // HashCode and Equals
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;

        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(email, client.email)
                && Objects.equals(phone, client.phone) && Objects.equals(address, client.address)
                && Objects.equals(createdAt, client.createdAt) && Objects.equals(updatedAt, client.updatedAt)
                && Objects.equals(supportTickets, client.supportTickets);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(phone);
        result = 31 * result + Objects.hashCode(address);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        result = 31 * result + Objects.hashCode(supportTickets);
        return result;
    }
}
