package servlets;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import exhibition.ExhibitionDAO;
import utils.CurrentExhibitionHandler;

/**
 * Application Lifecycle Listener implementation class BootListener
 *
 */
@WebListener
public class BootListener implements ServletContextListener {

	private ScheduledExecutorService scheduler;
	
	@EJB
	private ExhibitionDAO ex;
    /**
     * Default constructor. 
     */
    public BootListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         System.out.println("Hillbilly 1 has died. RIP.");
         this.scheduler.shutdown();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
    	CurrentExhibitionHandler task = new CurrentExhibitionHandler(ex);
    	System.out.println("Hallelooooojah! This is hillbilly 1!");
    	
    	
    	scheduler = Executors.newSingleThreadScheduledExecutor();
    	scheduler.execute(task);
    }
	
}
