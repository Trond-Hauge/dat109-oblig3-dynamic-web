package utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import exhibition.Exhibition;
import exhibition.ExhibitionDAO;

/**
 * @author trond
 *
 * Runnable class that checks and determines whether Exhibition entities should be active or not.
 */
public class CurrentExhibitionHandler implements Runnable {
	
	private ExhibitionDAO ex;
	
	public CurrentExhibitionHandler(ExhibitionDAO ex) {
		this.ex = ex;
	}
	
	/**
	 * Checks time for exhibiton object, and manages activation/ deactivation in database.
	 * 
	 * @param exhibition object
	 */
	private void evaluateTime(Exhibition e) {
		if (!e.isActive() && e.getStart().isBefore(LocalDateTime.now()) && e.getStop().isAfter(LocalDateTime.now())) {
			e.setActive(true);
			ex.updateExhibition(e);
			System.out.println("Exhibition " + e.getName() + " had its active status changed to true.");
		} 
		if(e.isActive() && (e.getStart().isAfter(LocalDateTime.now()) || e.getStop().isBefore(LocalDateTime.now()))){
			e.setActive(false);
			ex.updateExhibition(e);
			System.out.println("Exhibition " + e.getName() + " had its active status changed to false.");
		}
	}
	
	@Override
	public void run() {		
		
		while (true) {
			long startTime = System.nanoTime();
			
			//Turn on by uncommenting the line under
			ex.getAllExhibitions().forEach(e -> evaluateTime(e));
			
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
