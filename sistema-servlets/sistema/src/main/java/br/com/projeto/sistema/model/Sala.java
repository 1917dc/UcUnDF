package br.com.projeto.sistema.model;

public class Sala {
	private int numero;
	private int capacidade;

	public Sala(int i, int j, int capacidade) {
		this.numero = i;
		this.capacidade = capacidade;
	}

	public int getNome() {
		return numero;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setNome(int nome) {
		this.numero = nome;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
}
