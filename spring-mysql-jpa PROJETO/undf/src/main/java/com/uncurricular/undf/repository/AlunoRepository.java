package com.uncurricular.undf.repository;

import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
