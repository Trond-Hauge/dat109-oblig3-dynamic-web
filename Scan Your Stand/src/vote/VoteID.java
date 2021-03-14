package vote;

import java.io.Serializable;

public class VoteID implements Serializable{

	private String phone;
	private String standID;
	
	public VoteID(){
	}
	
	public VoteID(String phone, String standID) {
		
		this.phone = phone;
		this.standID = standID;
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
		if (standID == null) {
			if (other.standID != null)
				return false;
		} else if (!standID.equals(other.standID))
			return false;
		return true;
	}

}
