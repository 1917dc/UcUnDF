package com.uncurricular.undf.repository;

import com.uncurricular.undf.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findProfessorByCpfAndSenha(String cpf, String senha);
    Professor findProfessorByCpf(String cpf);
}
