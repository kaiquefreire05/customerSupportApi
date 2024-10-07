package com.api.customersupport.infrastructure.repositories;

import com.api.customersupport.infrastructure.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackEntityRepository extends JpaRepository<FeedbackEntity, Long> {
}
