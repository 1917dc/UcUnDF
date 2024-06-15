package org.un.undf.model;

import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/api/aluno")
public class AlunoServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(AlunoDB.fetchAll().toString());
        response.getWriter().flush();
        response.getWriter().close();
    }

}
