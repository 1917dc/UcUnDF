package com.uncurricular.undf.repository;

import com.uncurricular.undf.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findAlunoByCpfAndSenha(String cpf, String senha);
}
