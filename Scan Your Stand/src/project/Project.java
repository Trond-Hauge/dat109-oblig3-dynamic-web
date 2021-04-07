package project;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import exhibition.Exhibition;
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

	@OneToMany(mappedBy = "project")
	private List<Vote> votes;
	
	@ManyToOne
	@JoinColumn(name = "exhibitionId", referencedColumnName = "id")
	private Exhibition exhibition;
	
	public Project() {}

	public Project(String projectNumber, String projectName) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
	}
	
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
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

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "Project [projectNumber=" + projectNumber + ", projectName=" + projectName + ", votes=" + votes + "]";
	}

	public Exhibition getExhibition() {
		return exhibition;
	}

}