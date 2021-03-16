package vote;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import stand.Stand;

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
	private String standID;
	private int points;
	
	@ManyToOne
	@JoinColumn(name = "standID", referencedColumnName = "standID")
	private Stand stand;

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
