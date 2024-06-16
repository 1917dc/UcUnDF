package org.un.undf.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.un.undf.model.Aluno;
import org.un.undf.model.AlunoDB;

@WebServlet("/aluno")
public class AlunoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGetAlunos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Gson json = new Gson();
		List<Aluno> alunos = AlunoDB.fetchAllAlunos();
		
		String jsonString = json.toJson(alunos);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonString);
	}


	protected void doGetAluno(HttpServletRequest request, HttpServletResponse response, String cpf) throws IOException{
		Gson json = new Gson();
		Aluno aluno = AlunoDB.fetchAluno(cpf);

		String jsonString = json.toJson(aluno);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("senha");
		String curso = request.getParameter("curso");
		
		AlunoDB.add(new Aluno(nome, cpf, senha, curso));
	}
}
