package com.api.customersupport.infrastructure.controllers;

import com.api.customersupport.domain.exceptions.FeedbackNotFoundException;
import com.api.customersupport.domain.models.Feedback;
import com.api.customersupport.infrastructure.dto.requests.feedback.ProvideFeedbackRequest;
import com.api.customersupport.infrastructure.dto.requests.feedback.UpdateFeedbackRequest;
import com.api.customersupport.infrastructure.dto.response.BaseResponse;
import com.api.customersupport.usecases.feedback.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@RestController
@RequestMapping("api/v1/feedback")
public class FeedbackController {
    // Dependency Injection
    private final FindFeedbackByIdUseCase findFeedbackByIdUseCase;
    private final DeleteFeebackUseCase deleteFeebackUseCase;
    private final FindFeedbackByTicketIdUseCase findFeedbackByTicketIdUseCase;
    private final ProvideFeedbackUseCase provideFeedbackUseCase;
    private final UpdateFeedbackUseCase updateFeedbackUseCase;

    public FeedbackController(FindFeedbackByIdUseCase findFeedbackByIdUseCase, DeleteFeebackUseCase deleteFeebackUseCase
            , FindFeedbackByTicketIdUseCase findFeedbackByTicketIdUseCase, ProvideFeedbackUseCase provideFeedbackUseCase
            , UpdateFeedbackUseCase updateFeedbackUseCase) {
        this.findFeedbackByIdUseCase = findFeedbackByIdUseCase;
        this.deleteFeebackUseCase = deleteFeebackUseCase;
        this.findFeedbackByTicketIdUseCase = findFeedbackByTicketIdUseCase;
        this.provideFeedbackUseCase = provideFeedbackUseCase;
        this.updateFeedbackUseCase = updateFeedbackUseCase;
    }

    // Controller Methods
    @GetMapping("id/{id}")
    public ResponseEntity<BaseResponse<Feedback>> getById(@PathVariable Long id) {
        log.info("Request received to get feedback by id: {}::FeedbackController", id);
        try {
            Feedback feedback = findFeedbackByIdUseCase.findFeedbackById(id);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Feedback>builder().success(true)
                    .result(feedback).message("Feedback with id " + id + " successfully returned.").build());
        } catch (FeedbackNotFoundException ex) {
            log.error("Feedback with id {} not found::FeedbackController", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.<Feedback>builder().success(false)
                    .result(null).message("Feedback with id " + id + " not found.").build());

        } catch (Exception ex) {
            log.error("Error occurred while getting feedback with id {}::FeedbackController", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Feedback>builder().success(false)
                    .result(null).message("Error occurred while getting feedback with id " + id).build());
        }
    }

    @GetMapping("ticketId/{ticketId}")
    public ResponseEntity<BaseResponse<Feedback>> getByTicketId(@PathVariable Long ticketId) {
        log.info("Request received to get feedback by ticketId: {}::FeedbackController", ticketId);
        try {
            Feedback feedback = findFeedbackByTicketIdUseCase.findFeedbackByTicketId(ticketId);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Feedback>builder().success(true)
                    .result(feedback).message("Feedback with ticketId " + ticketId + " successfully returned.").build());
        } catch (Exception ex) {
            log.error("Error occurred while getting feedback with ticketId {}::FeedbackController", ticketId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Feedback>builder().success(false)
                    .result(null).message("Error occurred while getting feedback with ticketId " + ticketId).build());
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<BaseResponse<Boolean>> delete(@PathVariable Long id) {
        log.info("Request received to delete feedback by id: {}::FeedbackController", id);
        try {
            deleteFeebackUseCase.deleteFeedback(id);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Boolean>builder().success(true)
                    .message("Feedback with id " + id + " successfully deleted.").build());

        } catch (Exception ex) {
            log.error("Error occurred while deleting feedback with id {}::FeedbackController", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Boolean>builder().success(false)
                    .result(false).message("Error occurred while deleting feedback with id " + id).build());
        }
    }

    @PostMapping("/provide")
    public ResponseEntity<BaseResponse<String>> createFeedback(@Valid @RequestBody ProvideFeedbackRequest request) {
        log.info("Request received to provide feedback::FeedbackController");
        try {
            int rating = request.rating() != null ? request.rating() : 0;
            Feedback feedback = new Feedback(
                    request.comments(),
                    request.rating(),
                    LocalDateTime.now(),
                    null,
                    null
            );
            provideFeedbackUseCase.provideFeedback(request.supportTicketId(), feedback);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<String>builder().success(true)
                    .message("Feedback successfully provided.").build());

        } catch (Exception ex) {
            log.error("Error occurred while providing feedback::FeedbackController");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder().success(false)
                    .message("Error occurred while providing feedback.").build());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse<String>> updateFeedback(@Valid @RequestBody UpdateFeedbackRequest request) {
        log.info("Request received to update feedback::FeedbackController");
        try {
            Feedback foundedFeedback = findFeedbackByIdUseCase.findFeedbackById(request.id());
            foundedFeedback.setComments(request.comments());
            foundedFeedback.setRating(request.rating());
            foundedFeedback.setUpdatedAt(LocalDateTime.now());

            updateFeedbackUseCase.updateFeedback(foundedFeedback);
            log.info("Feedback updated successfully::FeedbackController");
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<String>builder().success(true)
                    .message("Feedback updated successfully.").build());

        } catch (Exception ex) {
            log.error("Error occurred while updating feedback. Error details: {}::FeedbackController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder().success(false)
                    .message("Error occurred while updating feedback.").build());
        }
    }
}