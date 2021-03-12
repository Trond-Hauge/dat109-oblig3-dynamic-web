package stand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

	//Skal oppdateres til String her og i database
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int standID;
	String standName;

	public Stand(String standName) {
		this.standName = standName;
	}

	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
	}

	public int getStandID() {
		return standID;
	}

	@Override
	public String toString() {
		return "Stand [standID=" + standID + ", standName=" + standName + "]";
	}
	
	
}