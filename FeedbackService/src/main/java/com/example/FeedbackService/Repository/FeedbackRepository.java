package com.example.FeedbackService.Repository;

import com.example.FeedbackService.Domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<List<Feedback>> findByTaskId(Long id);

    Optional<Feedback> findByRequestId(Long id);

    Optional<List<Feedback>> findByHirerId(Long id);
}
