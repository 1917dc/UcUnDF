package com.uncurricular.undf.controller;


import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.model.Professor;
import com.uncurricular.undf.repository.AlunoRepository;
import com.uncurricular.undf.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/aluno")
    public boolean findAlunoByCpfAndSenha(String cpf, String senha){
        Optional<Aluno> aluno = alunoRepository.findAlunoByCpfAndSenha(cpf, senha);
        return aluno.isPresent();
    }
    @GetMapping("/professor")
    public boolean findProfessorByCpfAndSenha(String cpf, String senha){
        Optional<Professor> professor = professorRepository.findProfessorByCpfAndSenha(cpf, senha);
        return professor.isPresent();
    }


}
