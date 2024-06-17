package br.com.projeto.sistema.model;

import java.util.List;

public class Turma {
	List<Aluno> alunos;
	String nome;
	Professor professor;
	Sala sala;
	Disciplina disciplina;

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Turma(List<Aluno> alunos, String nome, Professor professor, Sala sala, Disciplina disciplina) {
		super();
		this.alunos = alunos;
		this.nome = nome;
		this.professor = professor;
		this.sala = sala;
		this.disciplina = disciplina;
	}

}
