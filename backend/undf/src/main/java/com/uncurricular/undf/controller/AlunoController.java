package com.uncurricular.undf.controller;

import com.uncurricular.undf.exception.ResourceNotFoundException;
import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.model.Turma;
import com.uncurricular.undf.model.TurmaAluno;
import com.uncurricular.undf.repository.AlunoRepository;
import com.uncurricular.undf.repository.TurmaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaAlunoRepository turmaAlunoRepository;

    @GetMapping
    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Aluno> findById(@PathVariable Long id){
        return alunoRepository.findById(id);
    }

    @GetMapping("/{id}/turmas")
    public List<Turma> findAllTurmasByAlunoId(@PathVariable Long id){
        List<TurmaAluno> turmaAlunos = turmaAlunoRepository.findTurmaAlunoByAlunoId(id);
        return turmaAlunos.stream()
                .map(TurmaAluno::getTurma)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/nota")
    public TurmaAluno updateNotaByAlunoId(@PathVariable Long id, @RequestBody Float nota) {
        TurmaAluno turmaAluno = turmaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TurmaAluno não encontrado com id " + id));
        turmaAluno.setNota(nota);
        turmaAlunoRepository.save(turmaAluno);
        return turmaAluno;
    }

    @GetMapping("/aluno")
    public Aluno findAlunoByCpf(String cpf){
        return alunoRepository.findAlunoByCpf(cpf);
    }

    @GetMapping("/turma/{aluno_id}")
    public Optional<TurmaAluno> findAlunoInTurmaById(@PathVariable Long aluno_id){
        return turmaAlunoRepository.findById(aluno_id);
    }
}
