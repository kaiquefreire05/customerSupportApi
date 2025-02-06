package com.api.customersupport.application.ports.output.availability;

public interface ClientEmailAvailabilityPort {
    Boolean isClientEmailAvailable(String email);
}
