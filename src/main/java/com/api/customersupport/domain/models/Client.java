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

public class Client {
    // Attributes
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<SupportTicket> supportTickets;

    // Constructors
    public Client(UUID id, String name, String email, String password, String phone, String address, LocalDateTime createdAt
            , LocalDateTime updatedAt, List<SupportTicket> supportTickets) throws EmailInvalidException, PhoneInvalidException {
        this.id = id;
        this.name = name;
        setEmail(email);
        this.password = password;
        setPhone(phone);
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.supportTickets = supportTickets;
    }

    public Client(String name, String email, String password, String phone, String address, LocalDateTime createdAt
            , LocalDateTime updatedAt, List<SupportTicket> supportTickets) throws EmailInvalidException, PhoneInvalidException {
        this.name = name;
        setEmail(email);
        this.password = password;
        setPhone(phone);
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.supportTickets = supportTickets;
    }

    public Client(UUID id, String name, String email, String password, String phone, String address
            , LocalDateTime createdAt, LocalDateTime updatedAt) throws EmailInvalidException, PhoneInvalidException {
        this.id = id;
        this.name = name;
        setEmail(email);
        this.password = password;
        setPhone(phone);
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws PhoneInvalidException {
        if (!isValidPhone(phone)) {
            throw new PhoneInvalidException(ErrorCodeEnum.ON0002.getMessage(), ErrorCodeEnum.ON0002.getCode());
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

        return id.equals(client.id) && name.equals(client.name) && email.equals(client.email) && password.equals(client.password) && phone.equals(client.phone) && address.equals(client.address) && createdAt.equals(client.createdAt) && Objects.equals(updatedAt, client.updatedAt) && supportTickets.equals(client.supportTickets);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + Objects.hashCode(updatedAt);
        result = 31 * result + supportTickets.hashCode();
        return result;
    }
}
