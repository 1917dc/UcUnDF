package br.com.projeto.sistema;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import br.com.projeto.sistema.controller.AlunosServlet;
import br.com.projeto.sistema.controller.LoginServlet;
import br.com.projeto.sistema.controller.TurmasAlunoServlet;
import br.com.projeto.sistema.controller.TurmasProfServlet;

public class Application {

    public static void main(String[] args) throws LifecycleException {
    	Tomcat tomcat = new Tomcat();
    	tomcat.setPort(8080);
    	tomcat.getConnector();
    	
    	Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
    	Tomcat.addServlet(ctx, "LoginServlet", new LoginServlet());
    	ctx.addServletMappingDecoded("/login", "LoginServlet");
    	
    	Tomcat.addServlet(ctx, "AlunosServlet", new AlunosServlet());
    	ctx.addServletMappingDecoded("/alunos", "AlunosServlet");
    	
    	Tomcat.addServlet(ctx, "TurmasProfServlet", new TurmasProfServlet());
    	ctx.addServletMappingDecoded("/turmas/prof", "TurmasProfServlet");
    	
    	Tomcat.addServlet(ctx, "TurmasAlunServlet", new TurmasAlunoServlet());
    	ctx.addServletMappingDecoded("/turmas/aluno", "TurmasAlunServlet");
    	
    	tomcat.start();
    	tomcat.getServer().await();
    }
}
