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

import br.com.projeto.sistema.model.Turma;
import br.com.projeto.sistema.model.TurmasProfDB;

@WebServlet("/turmas/prof")
public class TurmasProfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();
		
		List<Turma> turmas = new ArrayList<>();
		String professorCpf = request.getParameter("cpf");
		
		if (professorCpf == null || professorCpf.isEmpty()) {
			turmas = TurmasProfDB.getAll();
		} else {
			turmas = TurmasProfDB.getFiltered(professorCpf);
		}
		
		String jsonString = json.toJson(turmas);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonString);
	    
	    return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
