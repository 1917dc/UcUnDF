package com.uncurricular.undf.repository;

import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.model.Turma;
import com.uncurricular.undf.model.TurmaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno, Long> {
    List<TurmaAluno> findByTurmaId(Long turmaId);
}
