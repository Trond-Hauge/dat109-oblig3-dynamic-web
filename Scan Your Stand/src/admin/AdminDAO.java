package admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdminDAO {

	@PersistenceContext(name = "scanyourstandUP")
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

	/**
	 * Add administrator
	 * 
	 * @param admin
	 */
	public void addAdmin(Admin admin) {
		em.persist(admin);
	}
	
	/**
	 * Remove administrator
	 * 
	 * @param Admin
	 */
	public void removeAdmin(Admin admin) {
		em.remove(admin);
	}
}
