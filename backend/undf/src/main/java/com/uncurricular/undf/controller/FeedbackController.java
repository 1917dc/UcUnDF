package com.uncurricular.undf.controller;
import com.uncurricular.undf.exception.ResourceNotFoundException;
import com.uncurricular.undf.model.Aluno;
import com.uncurricular.undf.model.Turma;
import com.uncurricular.undf.model.Feedback;
import com.uncurricular.undf.repository.AlunoRepository;
import com.uncurricular.undf.repository.FeedbackRepository;
import com.uncurricular.undf.repository.ProfessorRepository;
import com.uncurricular.undf.repository.TurmaRepository;
import com.uncurricular.undf.model.Professor;
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
    public Feedback createFeedback(@PathVariable Long aluno_id, @RequestBody Feedback feedback) {
        Aluno aluno = alunoRepository.findById(aluno_id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id " + aluno_id));

        Turma turma = turmaRepository.findById(feedback.getTurma().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada com id " + feedback.getTurma().getId()));

        Professor professor = professorRepository.findById(feedback.getProfessor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com id " + feedback.getProfessor().getId()));

        feedback.setAluno(aluno);
        feedback.setTurma(turma);
        feedback.setProfessor(professor);

        return feedbackRepository.save(feedback);
    }

}
