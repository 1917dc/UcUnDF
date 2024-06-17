package br.com.projeto.sistema.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.projeto.sistema.model.Aluno;
import br.com.projeto.sistema.model.AlunosDB;

@WebServlet("/alunos")
public class AlunosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Gson json = new Gson();
		
		String cpf = request.getParameter("cpf");
		List<Aluno> alunos = new ArrayList<>();
		
		if (cpf == null || cpf.isEmpty()) {
			alunos = AlunosDB.getAll();
		} else {
			alunos = AlunosDB.getFiltered(cpf);
		}
		
		String jsonString = json.toJson(alunos);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonString);
	    
	    return;
	    
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("senha");
		String curso = request.getParameter("curso");
		
		AlunosDB.add(new Aluno(nome, cpf, senha, curso));
	}
}
