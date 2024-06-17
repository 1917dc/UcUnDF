package com.uncurricular.undf.controller;

import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.model.Professor;
import com.uncurricular.undf.model.Turma;
import com.uncurricular.undf.model.TurmaAluno;
import com.uncurricular.undf.repository.ProfessorRepository;
import com.uncurricular.undf.repository.TurmaAlunoRepository;
import com.uncurricular.undf.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaAlunoRepository turmaAlunoRepository;

    @GetMapping
    public List<Professor> findAll(){
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Professor> findById (@PathVariable Long id){
        return professorRepository.findById(id);
    }

    @GetMapping("/{id}/turmas")
    public List<Turma> findTurmaByProfessorId(@PathVariable Long id){
        return turmaRepository.findTurmasByProfessorId(id);
    }

}
