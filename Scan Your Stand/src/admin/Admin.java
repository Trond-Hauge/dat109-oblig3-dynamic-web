package admin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import utils.BCrypt;

@Entity
@Table(schema = "scanyourstand")
public class Admin {

	@Id
	private String username;
	private String hashedPassword;
	
	public Admin(String username, String password) {
		this.username = username;
		this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}


}
