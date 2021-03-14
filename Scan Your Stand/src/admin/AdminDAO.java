package admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdminDAO {

	@PersistenceContext(name = "scanyourstand")
	EntityManager em;
	
	/**
	 * Find the admin by username
	 * 
	 * @param username
	 * @return
	 */
	public Admin findAdmin(String username) {
		return em.find(Admin.class, username);
	}

}
