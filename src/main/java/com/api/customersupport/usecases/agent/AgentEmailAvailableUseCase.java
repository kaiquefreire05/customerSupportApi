package com.api.customersupport.usecases.agent;

import com.api.customersupport.domain.exceptions.EmailUnavailableException;

public interface AgentEmailAvailableUseCase {
    Boolean isEmailAvailable(String email) throws EmailUnavailableException;
}
