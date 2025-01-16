package com.api.customersupport.infrastructure.jpa;

import com.api.customersupport.infrastructure.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FeedbackEntityRepository extends JpaRepository<FeedbackEntity, Long> {
    @Query("SELECT f FROM FeedbackEntity f WHERE f.supportTicket.id = :ticketId")
    Optional<FeedbackEntity> findFeedbackByTicketId(@Param("ticketId") Long ticketId);
}
