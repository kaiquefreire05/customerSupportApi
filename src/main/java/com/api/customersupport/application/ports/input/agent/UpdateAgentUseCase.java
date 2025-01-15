package com.api.customersupport.application.ports.input.agent;

import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Agent;

public interface UpdateAgentUseCase {
    Agent updateAgent(Agent agent) throws AgentNotFoundException, EmailUnavailableException, EmailInvalidException, PhoneInvalidException;
}
