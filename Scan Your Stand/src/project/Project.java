package project;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import vote.Vote;

/**
 * Defines the Stand used in ScanYourStand solution
 * 
 * @author Ida
 *
 */
@Entity
@Table(schema = "scanyourstand")
public class Project {

	@Id
	private String projectNumber; 
	private String projectName;
	private String standNumber;
	
	@OneToMany(mappedBy = "stand")
	private List<Vote> votes;
	
	public Project(String projectNumber, String projectName) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.standNumber = null;
	}

	public Project(String projectNumber, String projectName, String standNumber) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.standNumber = standNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNumber() {
		return projectNumber;
	}
	
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getStandNumber() {
		return standNumber;
	}

	public void setStandNumber(String standNumber) {
		this.standNumber = standNumber;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "Project [projectNumber=" + projectNumber + ", projectName=" + projectName + ", standNumber=" + standNumber
				+ ", votes=" + votes + "]";
	}
	
	
	
}