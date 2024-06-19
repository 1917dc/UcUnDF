package com.uncurricular.undf.repository;

import com.uncurricular.undf.model.Laudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaudoRepository extends JpaRepository<Laudo, Long> {
    Optional<Laudo> findLaudoByAlunoId(Long id);
}
