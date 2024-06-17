package br.com.projeto.sistema.model;

public class Disciplina {
	private int id;
	private String nome;
	private String descricao;
	private String cargaHoraria;

	public Disciplina(int id, String nome, String descricao, String cargaHoraria) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.cargaHoraria = cargaHoraria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}
