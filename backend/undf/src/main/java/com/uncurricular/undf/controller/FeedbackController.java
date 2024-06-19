package com.uncurricular.undf.controller;

import com.uncurricular.undf.model.Feedback;
import com.uncurricular.undf.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
