package vote;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import project.Project;
import utils.BCrypt;
import utils.Hashing;

/**
 * @author Ida
 * @author Anders
 * 
 */
@Entity
@Table(schema = "scanyourstand")
@IdClass(VoteID.class)
public class Vote implements Serializable{
	
	@Id
	private String phone;
	@Id
	private String projectNumber;
	private int points;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "projectNumber", referencedColumnName = "projectNumber")
	private Project project;
	
	public Vote() {}

	public Vote(String phone, String projectNumber) {
		this.phone = Hashing.hashPhone(phone);
		this.projectNumber = projectNumber;
	}
	public Vote(String phone, String projectNumber, int points) {
		this.phone = Hashing.hashPhone(phone);
		this.projectNumber = projectNumber;
		this.points = points;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}
	
	
	
	
}
