package vote;

import java.io.Serializable;

/**
 * 
 * @author anders
 * 
 * Class for composite key
 *
 */

public class VoteID implements Serializable{

	private String phone;
	private String projectNumber;
	
	public VoteID(){
	}
	
	public VoteID(String phone, String projectNumber) {
		
		this.phone = phone;
		this.projectNumber = projectNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteID other = (VoteID) obj;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (projectNumber == null) {
			if (other.projectNumber != null)
				return false;
		} else if (!projectNumber.equals(other.projectNumber))
			return false;
		return true;
	}

}
