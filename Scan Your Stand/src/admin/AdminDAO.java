package admin;

<<<<<<< HEAD
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
=======
public class AdminDAO {
>>>>>>> branch 'master' of https://github.com/h586634/dat109-oblig3-dynamic-web.git

}
