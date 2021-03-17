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
	private String standName;
	private String standNumber;
	
	@OneToMany(mappedBy = "stand")
	private List<Vote> votes;
	
	public Project(String projectNumber, String standName) {
		this.projectNumber = projectNumber;
		this.standName = standName;
		this.standNumber = null;
	}

	public Project(String projectNumber, String standName, String standNumber) {
		this.projectNumber = projectNumber;
		this.standName = standName;
		this.standNumber = standNumber;
	}

	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
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
		return "Project [projectNumber=" + projectNumber + ", standName=" + standName + ", standNumber=" + standNumber
				+ ", votes=" + votes + "]";
	}
	
	
	
}