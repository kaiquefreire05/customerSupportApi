package com.api.customersupport.application.ports.output;

public interface ClientEmailAvailabilityPort {
    Boolean isClientEmailAvailable(String email);
}
