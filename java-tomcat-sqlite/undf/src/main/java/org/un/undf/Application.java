package org.un.undf;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import org.un.undf.controller.AlunoServlet;

public class Application {

    public static void main(String[] args) throws LifecycleException {
    	Tomcat tomcat = new Tomcat();
    	tomcat.setPort(8080);
    	tomcat.getConnector();
    	
    	Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
    	Tomcat.addServlet(ctx, "AlunoServlet", new AlunoServlet());
    	ctx.addServletMappingDecoded("/aluno", "AlunoServlet");
    	
    	tomcat.start();
    	tomcat.getServer().await();
    }
}
