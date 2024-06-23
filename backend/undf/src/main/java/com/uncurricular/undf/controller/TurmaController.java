package com.uncurricular.undf.controller;

import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.model.Turma;
import com.uncurricular.undf.model.TurmaAluno;
import com.uncurricular.undf.repository.TurmaAlunoRepository;
import com.uncurricular.undf.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaAlunoRepository turmaAlunoRepository;


    @GetMapping
    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Turma findById(@PathVariable Long id) {
        return turmaRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/alunos")
    public List<TurmaAluno> findAllAlunosTurma(@PathVariable Long id){
        return turmaAlunoRepository.findByTurmaId(id);
    }

    @GetMapping("/{turma_id}/aluno/{aluno_id}")
    public Aluno findAlunoByTurmaId(@PathVariable Long turma_id, @PathVariable Long aluno_id){
        List<TurmaAluno> turmaAlunos = turmaAlunoRepository.findByTurmaId(turma_id);
        Aluno aluno = null;

        for(TurmaAluno turmaAlunosEach : turmaAlunos){
            if(aluno_id.equals(turmaAlunosEach.getAluno().getId())){
                aluno = turmaAlunosEach.getAluno();
            }
        }
        return aluno;
    }

    @GetMapping("/aluno/{aluno_id}")
    public List<TurmaAluno> findTurmasByAlunoId(@PathVariable Long aluno_id){
        return turmaAlunoRepository.findTurmaAlunoByAlunoId(aluno_id);
    }

}