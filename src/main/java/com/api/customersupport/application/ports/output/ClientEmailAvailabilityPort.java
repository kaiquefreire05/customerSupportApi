package com.api.customersupport.application.ports.output;

public interface ClientEmailAvailabilityPort {
    Boolean isEmailAvailable(String email);
}
