package com.uncurricular.undf.controller;


import com.uncurricular.undf.model.Laudo;
import com.uncurricular.undf.repository.LaudoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laudos")
public class LaudoController {

    private LaudoRepository laudoRepository;

    @GetMapping
    public List<Laudo> findAll(){
        return laudoRepository.findAll();
    }
    @GetMapping("/{aluno_id}")
    public Optional<Laudo> findLaudoByAlunoId(@PathVariable Long aluno_id){
        return laudoRepository.findLaudoByAlunoId(aluno_id);
    }
}
