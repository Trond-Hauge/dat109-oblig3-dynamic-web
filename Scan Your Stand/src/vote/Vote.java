package vote;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 
 * @author Ida
 * @author Anders
 *
 */
@Entity
@Table(schema = "scanyourstand")
@IdClass(VoteID.class)
public class Vote {
	
	@Id
	private String phone;
	@Id
	private String standID;
	private int points;

	public Vote(String phone, String standID) {
		this.phone = phone;
		this.standID = standID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStandID() {
		return standID;
	}

	public void setStandID(String standID) {
		this.standID = standID;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}
	
	
}
