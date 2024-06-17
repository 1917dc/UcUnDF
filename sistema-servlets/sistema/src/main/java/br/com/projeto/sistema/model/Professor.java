package br.com.projeto.sistema.model;

public class Professor {
	private int id;
	private String nome;
	private String cpf;
	private String senha;

	public Professor(int id, String nome, String cpf, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
