package com.uncurricular.undf.controller;

import com.uncurricular.undf.model.Feedback;
import com.uncurricular.undf.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping
    public List<Feedback> findAll(){
        return feedbackRepository.findAll();
    }

    @GetMapping("/aluno/{aluno_id}")
    public Feedback findFeedbackByAlunoId(@PathVariable Long aluno_id){
        return feedbackRepository.findFeedbackByAlunoId(aluno_id);
    }

    @PostMapping("/aluno/post/{turma_id}/{professor_id}/{aluno_id}")
    public Feedback postFeedbackByAlunoId(@PathVariable Long aluno_id, String titulo, String corpo, @PathVariable Long professor_id, @PathVariable Long turma_id){
        System.out.println(titulo);
        System.out.println(corpo);

        return null;
    }
}
