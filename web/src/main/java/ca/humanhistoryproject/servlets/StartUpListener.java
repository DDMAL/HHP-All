package ca.humanhistoryproject.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ca.humanhistoryproject.GlobalVars;

/**
 * Application Lifecycle Listener implementation class StartUpListener
 *
 */
public class StartUpListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public StartUpListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        if (GlobalVars.init_on_startup)
        	GlobalVars.initialize();
    }
	
}
