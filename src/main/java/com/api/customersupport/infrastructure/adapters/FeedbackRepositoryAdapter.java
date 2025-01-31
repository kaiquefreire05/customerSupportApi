package com.api.customersupport.infrastructure.adapters;

import com.api.customersupport.application.mapper.FeedbackMapper;
import com.api.customersupport.application.ports.output.FeedbackRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.infrastructure.entities.FeedbackEntity;
import com.api.customersupport.infrastructure.jpa.FeedbackEntityRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Repository
public class FeedbackRepositoryAdapter implements FeedbackRepositoryPort {
    // Dependency Injection
    private final FeedbackEntityRepository feedbackRepository;
    private final FeedbackMapper mapper;

    public FeedbackRepositoryAdapter(FeedbackEntityRepository feedbackRepository, FeedbackMapper mapper) {
        this.feedbackRepository = feedbackRepository;
        this.mapper = mapper;
    }

    @Override
    public Boolean deleteFeedback(Long id) {
        try {
            log.info("Deleting feedback with id: {}::FeedbackRepositoryAdapter", id);
            feedbackRepository.deleteById(id);
            log.info("Feedback with id: {} deleted successfully::FeedbackRepositoryAdapter", id);
            return true;
        } catch (Exception ex) {
            log.error("Error deleting feedback with id: {}::FeedbackRepositoryAdapter", id);
            return false;
        }
    }

    @Override
    public Feedback findFeedbackById(Long id) throws FeedbackNotFoundException {
        log.info("Finding feedback with id: {}::FeedbackRepositoryAdapter", id);
        return feedbackRepository.findById(id).map(mapper::toDomainModel)
                .orElseThrow(() -> new FeedbackNotFoundException(ErrorCodeEnum.FD0004.getCode(),
                        ErrorCodeEnum.FD0004.getCode()));
    }

    @Override
    public Feedback findFeedbackByTicketId(Long ticketId) throws FeedbackNotFoundException {
        log.info("Finding feedback with ticket id: {}::FeedbackRepositoryAdapter", ticketId);
        return feedbackRepository.findFeedbackByTicketId(ticketId).map(mapper::toDomainModel)
                .orElseThrow(() -> new FeedbackNotFoundException(ErrorCodeEnum.FD0004.getCode(),
                        ErrorCodeEnum.FD0004.getCode()));
    }

    @Override
    public Boolean provideFeedback(Feedback feedback) {
        try {
            log.info("Providing feedback::FeedbackRepositoryAdapter");
            feedbackRepository.save(mapper.toEntity(feedback));
            log.info("Feedback provided successfully::FeedbackRepositoryAdapter");
            return true;
        } catch (Exception ex) {
            log.error("Error providing feedback::FeedbackRepositoryAdapter", ex);
            return false;
        }
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        try {
            log.info("Updating feedback::FeedbackRepositoryAdapter");
            var existentFeedback = feedbackRepository.findById(feedback.getId())
                    .orElseThrow(() -> new FeedbackNotFoundException(ErrorCodeEnum.FD0004.getCode(),
                            ErrorCodeEnum.FD0004.getMessage()));
            // Updating feedback and saving
            var update = mapper.toEntityUpdate(feedback);
            update.setUpdatedAt(LocalDateTime.now());
            log.info("Feedback updated successfully::FeedbackRepositoryAdapter");
            return mapper.toDomainModel(feedbackRepository.save(update));

        } catch (Exception ex) {
            log.error("Error updating feedback::FeedbackRepositoryAdapter", ex);
            return null;
        }
    }

    @Override
    public List<Feedback> listFeedbacks() {
        List<FeedbackEntity> feedbackEntities = feedbackRepository.findAll();
        return mapper.toDomainModelList(feedbackEntities);
    }
}
