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
import br.com.projeto.sistema.model.TurmasAlunoDB;

@WebServlet("/turmas/aluno")
public class TurmasAlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson json = new Gson();

		String cpf = req.getParameter("cpf");
		List<Turma> turmas = new ArrayList<>();

		if (cpf == null || cpf.isEmpty()) {
			turmas = TurmasAlunoDB.getAll();
		} else {
			turmas = TurmasAlunoDB.getFiltered(cpf);
		}

		String jsonString = json.toJson(turmas);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonString);

		return;
	}
}
