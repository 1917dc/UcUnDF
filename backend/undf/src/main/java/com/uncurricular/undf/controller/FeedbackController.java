package com.uncurricular.undf.controller;

import com.uncurricular.undf.model.Feedback;
import com.uncurricular.undf.repository.AlunoRepository;
import com.uncurricular.undf.repository.FeedbackRepository;
import com.uncurricular.undf.repository.ProfessorRepository;
import com.uncurricular.undf.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Feedback> findAll(){
        return feedbackRepository.findAll();
    }

    @GetMapping("/aluno/{aluno_id}")
    public Feedback findFeedbackByAlunoId(@PathVariable Long aluno_id){
        return feedbackRepository.findFeedbackByAlunoId(aluno_id);
    }

    @GetMapping("/turma/{turma_id}")
    public List<Feedback> findFeedbacksByTurmaId(@PathVariable Long turma_id) {
        return feedbackRepository.findFeedbacksByTurmaId(turma_id);
    }

    @PostMapping("/aluno/post/{aluno_id}")
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return null;
    }
}
