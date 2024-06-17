package br.com.projeto.sistema.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.sistema.model.Aluno;
import br.com.projeto.sistema.model.AlunosDB;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("senha");
		List<Aluno> alunos = AlunosDB.getAll();
		
		for (Aluno aluno : alunos) {
			if (aluno.getCpf().equals(cpf) && aluno.getSenha().equals(senha)) {
				response.getWriter().write("true");
				
				return;
			}
		}
		
		response.getWriter().write("false");
	}
}
