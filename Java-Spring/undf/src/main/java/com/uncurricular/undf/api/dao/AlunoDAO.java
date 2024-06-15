package com.uncurricular.undf.api.dao;

import com.uncurricular.undf.api.model.Aluno;

import java.util.List;
import java.util.Optional;

public class AlunoDAO implements DAO<Aluno> {

    @Override
    public List<Aluno> list() {
        return List.of();
    }

    @Override
    public void create(Aluno aluno) {

    }

    @Override
    public Optional<Aluno> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Aluno aluno, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
