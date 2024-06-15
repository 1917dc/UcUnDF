package com.uncurricular.undf.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Aluno {



    private String nome;
    private String cpf;
    private String curso;


    public Aluno() {
    }
    public Aluno(String nome, String cpf, String curso) {
        this.nome = nome;
        this.cpf = cpf;
        this.curso = curso;
    }

}

