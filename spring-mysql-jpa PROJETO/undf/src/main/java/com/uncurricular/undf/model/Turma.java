package com.uncurricular.undf.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private Professor professor;

    @OneToOne
    private Sala sala;

    @ManyToMany
    private List<TurmaAluno> alunos;



    public Turma() {
    }

    public Turma(String nome, Disciplina disciplina, Professor professor, Sala sala) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.professor = professor;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<TurmaAluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<TurmaAluno> alunos) {
        this.alunos = alunos;
    }
}
