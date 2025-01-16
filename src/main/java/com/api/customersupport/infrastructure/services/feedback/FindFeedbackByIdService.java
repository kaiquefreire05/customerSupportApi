package com.api.customersupport.infrastructure.services.feedback;

import com.api.customersupport.application.gateway.feedback.FindFeedbackByIdGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.application.mapper.FeedbackMapper;
import com.api.customersupport.infrastructure.jpa.FeedbackEntityRepository;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class FindFeedbackByIdService implements FindFeedbackByIdGateway {

    //Dependency injection
    private final FeedbackMapper feedbackMapper;
    private final FeedbackEntityRepository feedbackEntityRepository;

    public FindFeedbackByIdService(FeedbackMapper feedbackMapper, FeedbackEntityRepository feedbackEntityRepository) {
        this.feedbackMapper = feedbackMapper;
        this.feedbackEntityRepository = feedbackEntityRepository;
    }

    @Override
    public Feedback findFeedbackById(Long feedbackId) throws FeedbackNotFoundException {
        log.info("Finding feedback by id: {}::FindFeedbackByIdGatewayImpl", feedbackId);
        return feedbackEntityRepository.findById(feedbackId)
                .map(feedbackMapper::toDomainModel)
                .orElseThrow(() -> new FeedbackNotFoundException(ErrorCodeEnum.FD0004.getCode()
                        , ErrorCodeEnum.FD0004.getMessage()));
    }

}
