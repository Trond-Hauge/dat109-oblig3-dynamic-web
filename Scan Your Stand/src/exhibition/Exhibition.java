package exhibition;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "scanyourstand")
public class Exhibition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private boolean isActive;
	private LocalDate start;
	private LocalDate stop;
	
	public Exhibition() {};

	public Exhibition(String name, boolean isActive, LocalDate start, LocalDate stop) {
		this.name = name;
		this.isActive = isActive;
		this.start = start;
		this.stop = stop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getStop() {
		return stop;
	}

	public void setStop(LocalDate stop) {
		this.stop = stop;
	}

	public int getId() {
		return id;
	}

}
