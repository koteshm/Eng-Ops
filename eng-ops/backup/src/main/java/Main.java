import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) throws Exception {

    System.out.println("HERE");
   	Server server = new Server(Integer.parseInt(System.getenv("PORT")));

    //1.Creating the resource handler
    ResourceHandler resourceHandler= new ResourceHandler();

    //2.Setting Resource Base
    resourceHandler.setResourceBase(".");

    //3.Enabling Directory Listing
    resourceHandler.setDirectoriesListed(true);

    //4.Setting Context Source
    ContextHandler contextHandler= new ContextHandler("/");

    //5.Attaching Handlers
    contextHandler.setHandler(resourceHandler);
    server.setHandler(contextHandler);


    // Starting the Server

    server.start();
    System.out.println("Started!");
    server.join();
  }

}
