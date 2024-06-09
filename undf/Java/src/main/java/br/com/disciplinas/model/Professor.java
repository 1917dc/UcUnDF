package br.com.disciplinas.model;

import java.util.List;

public class Professor {
    private String professorNome;
    private String professorCPF;
    private String professorSenha;
    private List<Turma> professorTurmas;

    public Professor(String professorNome, String professorCPF, String professorSenha) {
        this.professorNome = professorNome;
        this.professorCPF = professorCPF;
        this.professorSenha = professorSenha;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    public String getProfessorCPF() {
        return professorCPF;
    }

    public void setProfessorCPF(String professorCPF) {
        this.professorCPF = professorCPF;
    }

    public String getProfessorSenha() {
        return professorSenha;
    }

    public void setProfessorSenha(String professorSenha) {
        this.professorSenha = professorSenha;
    }

    public List<Turma> getProfessorTurmas() {
        return professorTurmas;
    }

    public void setProfessorTurmas(List<Turma> professorTurmas) {
        this.professorTurmas = professorTurmas;
    }
}
