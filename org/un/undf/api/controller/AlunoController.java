package org.un.undf.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.un.undf.api.model.Aluno;
import org.un.undf.service.AlunoService;

import java.util.Optional;

@RestController
public class AlunoController {

    private AlunoService alunoService;


    @Autowired
    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @GetMapping("/aluno")
    public Aluno getAluno(@RequestParam String cpf){
        Optional<Aluno> aluno = alunoService.getAluno(cpf);
        if(aluno.isPresent()){
            return (Aluno) aluno.get();
        }
        return null;
    }
}
