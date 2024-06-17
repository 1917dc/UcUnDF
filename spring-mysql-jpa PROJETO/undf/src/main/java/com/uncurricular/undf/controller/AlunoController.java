package com.uncurricular.undf.controller;

import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Aluno> findById(@PathVariable Long id){
        return repository.findById(id);
    }
}
