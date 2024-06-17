package com.uncurricular.undf.controller;

import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.model.Turma;
import com.uncurricular.undf.model.TurmaAluno;
import com.uncurricular.undf.repository.AlunoRepository;
import com.uncurricular.undf.repository.TurmaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private TurmaAlunoRepository turmaAlunoRepository;

    @GetMapping
    public List<Aluno> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Aluno> findById(@PathVariable Long id){
        return repository.findById(id);
    }

    @GetMapping("/{id}/turmas")
    public List<Turma> findAllTurmasByAlunoId(@PathVariable Long id){
        List<TurmaAluno> turmaAlunos = turmaAlunoRepository.findTurmaAlunoByAlunoId(id);
        return turmaAlunos.stream()
                .map(TurmaAluno::getTurma)
                .collect(Collectors.toList());
    }
}
