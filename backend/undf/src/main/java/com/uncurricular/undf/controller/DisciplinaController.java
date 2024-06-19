package com.uncurricular.undf.controller;

import com.uncurricular.undf.model.Disciplina;
import com.uncurricular.undf.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @GetMapping
    public List<Disciplina> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Disciplina> findById(@PathVariable Long id){
        return repository.findById(id);
    }

    @GetMapping("/{id}/descricao")
    public String findDescricaoByDisciplinaId(@PathVariable Long id){
        Optional<Disciplina> disciplina = findById(id);
        return disciplina.get().getDescricao();
    }
}
