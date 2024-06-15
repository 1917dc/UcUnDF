package org.un.undf;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.un.undf.model.AlunoServlet;

import java.io.File;

public class Application {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();



        tomcat.start();
        tomcat.getServer().await();

    }
}
