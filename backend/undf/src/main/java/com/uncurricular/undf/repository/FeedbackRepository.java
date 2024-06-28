package com.uncurricular.undf.repository;

import com.uncurricular.undf.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findFeedbackByAlunoId(Long id);
    List<Feedback> findFeedbacksByTurmaId(Long turma_id);
}

