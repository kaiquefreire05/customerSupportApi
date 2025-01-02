package com.api.customersupport.infrastructure.services.feedback;

import com.api.customersupport.application.gateway.feedback.FindFeedbackByTicketIdGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.infrastructure.mapper.FeedbackMapper;
import com.api.customersupport.infrastructure.repositories.FeedbackEntityRepository;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class FindFeedbackByTicketIdService implements FindFeedbackByTicketIdGateway {
    // Dependency injection
    private final FeedbackMapper feedbackMapper;
    private final FeedbackEntityRepository feedbackEntityRepository;

    public FindFeedbackByTicketIdService(FeedbackMapper feedbackMapper
            , FeedbackEntityRepository feedbackEntityRepository) {
        this.feedbackMapper = feedbackMapper;
        this.feedbackEntityRepository = feedbackEntityRepository;
    }

    @Override
    public Feedback findFeedbackByTicketId(Long ticketId) throws FeedbackNotFoundException {
        log.info("Finding feedback by ticket id: {}::FindFeedbackByTicketIdGatewayImpl", ticketId);
        return feedbackEntityRepository.findFeedbackByTicketId(ticketId)
                .map(feedbackMapper::toDomainModel)
                .orElseThrow(() -> new FeedbackNotFoundException(ErrorCodeEnum.FD0004.getCode()
                        , ErrorCodeEnum.FD0004.getMessage()));
    }
}
