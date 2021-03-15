package stand;

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
public class Stand {

	@Id
	private String standID; //Clarify if this identification has a fixed length
	private String standName;
	
	@OneToMany(mappedBy = "stand")
	private List<Vote> votes;

	public Stand(String standID, String standName) {
		this.standID = standID;
		this.standName = standName;
	}

	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
	}

	public String getStandID() {
		return standID;
	}
	
	public void setStandID(String standID) {
		this.standID = standID;
	}
	

	@Override
	public String toString() {
		return "Stand [standID=" + standID + ", standName=" + standName + "]";
	}
	
	
}