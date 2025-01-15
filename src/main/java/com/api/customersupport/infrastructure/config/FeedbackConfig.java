package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.feedback.*;
import com.api.customersupport.application.ports.input.feedback.*;
import com.api.customersupport.application.services.feedback.*;
import com.api.customersupport.application.mapper.FeedbackMapper;
import com.api.customersupport.infrastructure.repositories.FeedbackEntityRepository;
import com.api.customersupport.application.ports.input.ticket.FindTicketByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackConfig {

    @Bean
    public FindFeedbackByIdUseCase findFeedbackByIdUseCase(FindFeedbackByIdGateway findFeedbackByIdGateway) {
        return new FindFeedbackByIdService(findFeedbackByIdGateway);
    }

    @Bean
    public DeleteFeebackUseCase deleteFeebackUseCase(DeleteFeedbackGateway deleteFeedbackGateway) {
        return new DeleteFeedbackService(deleteFeedbackGateway);
    }

    @Bean
    public DeleteFeedbackGateway deleteFeedbackGateway(FeedbackEntityRepository feedbackEntityRepository) {
        return new com.api.customersupport.infrastructure.services.feedback.DeleteFeedbackService(feedbackEntityRepository);
    }

    @Bean
    public FindFeedbackByTicketIdUseCase findFeedbackByTicketIdUseCase(FindFeedbackByTicketIdGateway findFeedbackByTicketIdGateway) {
        return new FindFeedbackByTicketIdService(findFeedbackByTicketIdGateway);
    }

    @Bean
    public FindFeedbackByTicketIdGateway findFeedbackByTicketIdGateway(FeedbackMapper feedbackMapper
            , FeedbackEntityRepository feedbackEntityRepository) {
        return new com.api.customersupport.infrastructure.services.feedback.FindFeedbackByTicketIdService(feedbackMapper, feedbackEntityRepository);
    }

    @Bean
    public ProvideFeedbackUseCase provideFeedbackUseCase(ProvideFeedbackGateway provideFeedbackGateway
            , FindTicketByIdUseCase findTicketByIdGateway) {
        return new ProvideFeedbackService(provideFeedbackGateway, findTicketByIdGateway);
    }

    @Bean
    public ProvideFeedbackGateway provideFeedbackGateway(FeedbackEntityRepository feedbackEntityRepository
            , FeedbackMapper feedbackMapper) {
        return new com.api.customersupport.infrastructure.services.feedback.ProvideFeedbackService(feedbackEntityRepository, feedbackMapper);
    }

    @Bean
    public UpdateFeedbackUseCase updateFeedbackUseCase(UpdateFeedbackGateway updateFeedbackGateway) {
        return new UpdateFeedbackService(updateFeedbackGateway);
    }

    @Bean
    public UpdateFeedbackGateway updateFeedbackGateway(FeedbackMapper feedbackMapper
            , FeedbackEntityRepository feedbackEntityRepository) {
        return new com.api.customersupport.infrastructure.services.feedback.UpdateFeedbackService(feedbackMapper, feedbackEntityRepository);
    }
}
