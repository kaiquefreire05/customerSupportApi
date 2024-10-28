package com.api.customersupport.usecases.client;

import com.api.customersupport.domain.exceptions.EmailUnavailableException;

public interface ClientEmailAvailableUseCase {
    Boolean isEmailAvailable(String email) throws EmailUnavailableException;
}