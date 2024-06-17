package com.uncurricular.undf.repository;

import com.uncurricular.undf.model.Professor;
import com.uncurricular.undf.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findTurmaByProfessorId(Long professorId);
}
