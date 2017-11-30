package com.bom.sbt;

import com.bom.sbt.restapi.GroupService;
import com.bom.sbt.restapi.GroupServiceImpl;
import com.bom.sbt.restapi.StudentService;
import com.bom.sbt.restapi.StudentServiceImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class StudentsServletModule extends ServletModule {
  @Override
  protected void configureServlets() {
    bind(GroupService.class).to(GroupServiceImpl.class);
    bind(StudentService.class).to(StudentServiceImpl.class);

    serve("/*").with(GuiceContainer.class);
  }

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    Injector injector = Guice.createInjector(new StudentsServletModule());

    ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
    servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

    servletContextHandler.addServlet(DefaultServlet.class, "/");

    server.start();
    server.join();
  }
}
