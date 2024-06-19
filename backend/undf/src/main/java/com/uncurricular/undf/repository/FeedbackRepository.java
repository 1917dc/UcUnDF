package com.uncurricular.undf.repository;

import com.uncurricular.undf.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findFeedbackByAlunoId(Long id);
}
