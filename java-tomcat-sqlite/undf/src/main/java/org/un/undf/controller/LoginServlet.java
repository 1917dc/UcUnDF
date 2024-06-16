package org.un.undf.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.un.undf.model.Aluno;
import org.un.undf.model.AlunoDB;

@WebServlet("/login")
public class LoginServlet {
	public Boolean logar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("senha");
		List<Aluno> alunos = AlunoDB.fetchAllAlunos();
		
		for (Aluno aluno : alunos) {
			if (aluno.getCpf().equals(cpf) && aluno.getSenha().equals(senha)) {
				return true;
			}
		}
		
		return false;
	}
}
