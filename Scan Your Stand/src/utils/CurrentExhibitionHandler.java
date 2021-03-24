package utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import exhibition.Exhibition;
import exhibition.ExhibitionDAO;

/**
 * 
 * @author trond
 *
 * Runnable class that checks and determines whether Exhibition entities should be active or not.
 */
public class CurrentExhibitionHandler implements Runnable {
	
	private Exhibition activeExhibition;
	private ExhibitionDAO ex;
	
	public CurrentExhibitionHandler(ExhibitionDAO ex) {
		this.ex = ex;
	}
	
	@Override
	public void run() {		
		
		while (true) {
			long startTime = System.nanoTime();
			
			LocalDateTime lt = LocalDateTime.now();
			
			try {
				List<Exhibition> list = ex.getAllExhibitions();
				
				//Check this list for exhibitions that need to be updates, or if ther eare conflicts.
				//NB: The default isActive value may need to be false. to be more dumb-proof. As long as the server is running, the thread should be running and controlling
				List<Exhibition> activeList = list.stream().filter(ex -> ex.isActive() == true).collect(Collectors.toList());
			} catch (Exception e) {
				System.out.println("ExhibitionDAO error");
			}
			
			//more to come
			
			System.out.println("Execution time in seconds: " + (double)((System.nanoTime() - startTime)/1_000_000_000));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Error: Sleep went wrong.");
				e.printStackTrace();
			}
		}
		
	}

}
