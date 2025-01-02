package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.feedback.*;
import com.api.customersupport.application.usecaseimpl.feedback.*;
import com.api.customersupport.infrastructure.mapper.FeedbackMapper;
import com.api.customersupport.infrastructure.repositories.FeedbackEntityRepository;
import com.api.customersupport.infrastructure.services.feedback.DeleteFeedbackService;
import com.api.customersupport.infrastructure.services.feedback.FindFeedbackByTicketIdService;
import com.api.customersupport.infrastructure.services.feedback.ProvideFeedbackService;
import com.api.customersupport.infrastructure.services.feedback.UpdateFeedbackService;
import com.api.customersupport.usecases.feedback.*;
import com.api.customersupport.usecases.ticket.FindTicketByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackConfig {

    @Bean
    public FindFeedbackByIdUseCase findFeedbackByIdUseCase(FindFeedbackByIdGateway findFeedbackByIdGateway) {
        return new FindFeedbackByIdImpl(findFeedbackByIdGateway);
    }

    @Bean
    public DeleteFeebackUseCase deleteFeebackUseCase(DeleteFeedbackGateway deleteFeedbackGateway) {
        return new DeleteFeedbackImpl(deleteFeedbackGateway);
    }

    @Bean
    public DeleteFeedbackGateway deleteFeedbackGateway(FeedbackEntityRepository feedbackEntityRepository) {
        return new DeleteFeedbackService(feedbackEntityRepository);
    }

    @Bean
    public FindFeedbackByTicketIdUseCase findFeedbackByTicketIdUseCase(FindFeedbackByTicketIdGateway findFeedbackByTicketIdGateway) {
        return new FindFeedbackByTicketIdImpl(findFeedbackByTicketIdGateway);
    }

    @Bean
    public FindFeedbackByTicketIdGateway findFeedbackByTicketIdGateway(FeedbackMapper feedbackMapper
            , FeedbackEntityRepository feedbackEntityRepository) {
        return new FindFeedbackByTicketIdService(feedbackMapper, feedbackEntityRepository);
    }

    @Bean
    public ProvideFeedbackUseCase provideFeedbackUseCase(ProvideFeedbackGateway provideFeedbackGateway
            , FindTicketByIdUseCase findTicketByIdGateway) {
        return new ProvideFeedbackImpl(provideFeedbackGateway, findTicketByIdGateway);
    }

    @Bean
    public ProvideFeedbackGateway provideFeedbackGateway(FeedbackEntityRepository feedbackEntityRepository
            , FeedbackMapper feedbackMapper) {
        return new ProvideFeedbackService(feedbackEntityRepository, feedbackMapper);
    }

    @Bean
    public UpdateFeedbackUseCase updateFeedbackUseCase(UpdateFeedbackGateway updateFeedbackGateway) {
        return new UpdateFeedbackImpl(updateFeedbackGateway);
    }

    @Bean
    public UpdateFeedbackGateway updateFeedbackGateway(FeedbackMapper feedbackMapper
            , FeedbackEntityRepository feedbackEntityRepository) {
        return new UpdateFeedbackService(feedbackMapper, feedbackEntityRepository);
    }
}
