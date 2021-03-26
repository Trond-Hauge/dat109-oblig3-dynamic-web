package exhibition;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import project.Project;

@Entity
@Table(schema = "scanyourstand")
public class Exhibition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private boolean isActive;
	private LocalDateTime start;
	private LocalDateTime stop;
	
	@OneToMany(mappedBy = "exhibition")
	private List<Project> projects;
	
	public Exhibition() {};

	public Exhibition(String name, boolean isActive, LocalDateTime start, LocalDateTime stop) {
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

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getStop() {
		return stop;
	}

	public void setStop(LocalDateTime stop) {
		this.stop = stop;
	}

	public int getId() {
		return id;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	

}
