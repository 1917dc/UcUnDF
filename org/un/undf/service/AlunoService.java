package org.un.undf.service;

import org.springframework.stereotype.Service;
import org.un.undf.api.model.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AlunoService {

    private List<Aluno> alunoList;

    public AlunoService(){
        alunoList = new ArrayList<>();

        var aluno = new Aluno("Leonardo", "1", "123", "Sis. Info");
        alunoList.add(aluno);
    }

    public Optional<Aluno> getAluno(String cpf) {
        Optional<Aluno> optional = Optional.empty();
        for(Aluno aluno : alunoList){
            if(cpf.equals(aluno.getCpf())){
                optional = Optional.of(aluno);
                return optional;
            }
        }
        return optional;
    }
}
