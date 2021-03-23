package codeSketches;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class startStopUtils {
	
	@Entity
	public class Exhibition implements Serializable{
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		private boolean isActive;
		private Date start;
		private Date stop;
		
		//If problems with date formatting, check:
		//https://stackabuse.com/how-to-get-current-date-and-time-in-java/
		
		public void startNow() {
			isActive = true;
			start = new Date(System.currentTimeMillis());
		}
		
		public void stopNow() {
			isActive = false;
			stop = new Date(System.currentTimeMillis());
		}
		
		//For the exhibition evaluator
		public boolean evaluateStartStop() {
			//temporary return
			return isActive;
			
			/*ifs and more ifs
			 * if(now() < start) {
			 * 	...
			 * }
			*/
		}
	}
	
	

}
