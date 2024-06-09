package br.com.disciplinas.model;

import br.com.caelum.stella.validation.CPFValidator;

import java.util.HashMap;

public class Aluno {
    private String alunoNome;
    private String alunoCPF;
    private String alunoSenha;
    private Curso alunoCurso;
    private HashMap<Turma, Double> alunoNotas;

    public Aluno(String alunoNome, String alunoCPF, String alunoSenha, Curso alunoCurso) {
        this.alunoNome = alunoNome;
        this.alunoCPF = alunoCPF;
        this.alunoSenha = alunoSenha;
        this.alunoCurso = alunoCurso;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAlunoCPF() {
        return alunoCPF;
    }

    public void setAlunoCPF(String alunoCPF) {
        this.alunoCPF = alunoCPF;
    }

    public String getAlunoSenha() {
        return alunoSenha;
    }

    public void setAlunoSenha(String alunoSenha) {
        this.alunoSenha = alunoSenha;
    }

    public Curso getAlunoCurso() {
        return alunoCurso;
    }

    public void setAlunoCurso(Curso alunoCurso) {
        this.alunoCurso = alunoCurso;
    }

    public HashMap<Turma, Double> getAlunoNotas() {
        return alunoNotas;
    }

    public void setAlunoNotas(HashMap<Turma, Double> alunoNotas) {
        this.alunoNotas = alunoNotas;
    }
}
