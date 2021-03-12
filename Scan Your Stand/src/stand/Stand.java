package stand;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	String standID;
	String standName;

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