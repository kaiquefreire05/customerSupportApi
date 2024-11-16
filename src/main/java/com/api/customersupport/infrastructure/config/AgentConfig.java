package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.agent.*;
import com.api.customersupport.application.gateway.client.ClientEmailAvailableGateway;
import com.api.customersupport.application.usecaseimpl.agent.*;
import com.api.customersupport.application.usecaseimpl.client.ClientEmailAvailableImpl;
import com.api.customersupport.infrastructure.mapper.AgentMapper;
import com.api.customersupport.infrastructure.repositories.AgentEntityRepository;
import com.api.customersupport.infrastructure.services.agent.AgentEmailAvailableGatewayImpl;
import com.api.customersupport.infrastructure.services.agent.DeleteAgentGatewayImpl;
import com.api.customersupport.infrastructure.services.agent.ListAgentsGatewayImpl;
import com.api.customersupport.usecases.agent.*;
import com.api.customersupport.usecases.client.ClientEmailAvailableUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

}
