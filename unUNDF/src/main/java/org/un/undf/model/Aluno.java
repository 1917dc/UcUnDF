package org.un.undf.model;

public class Aluno {
    private String nome;
    private String cpf;
    private String senha;
    private String curso;

    public Aluno(String nome, String cpf, String senha, String curso) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.curso = curso;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
