import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;

import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;
import com.infosys.engops.services.Actuals;

public class Main {

    public static void main(String[] args) throws Exception {
    	//System.setenv("DATABASE_URL", "postgres://postgres:postgres@localhost:5432/engopsDB");
    	//System.setProperty("PORT", "8080");
        System.out.println("Starting Spring");
        System.out.println(System.getenv("DATABASE_URL"));



        System.out.println("HERE");
        Server server = new Server(Integer.parseInt(System.getenv("PORT")));

        //1.Creating the resource handler
        ResourceHandler resourceHandler = new ResourceHandler();

        //2.Setting Resource Base
        resourceHandler.setResourceBase(".");

        //3.Enabling Directory Listing
        resourceHandler.setDirectoriesListed(true);

        //4.Setting Context Source
        ContextHandler contextHandler = new ContextHandler("/");

        //5.Attaching Handlers
        contextHandler.setHandler(resourceHandler);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        servletContextHandler.setContextPath("/");

        ServletHolder jerseyServlet = servletContextHandler.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.infosys.engops.services");



        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[]{contextHandler, servletContextHandler});
        server.setHandler(handlerCollection);
        
        // Starting the Server

        server.start();
        System.out.println("Started!");
        server.join();
        
    }

}
