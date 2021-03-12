package stand;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Defines the Data Access Object for Stand
 * 
 * @author Ida
 *
 */
@Stateless
public class StandDAO {

	@PersistenceContext(name = "standPU")
	private EntityManager em;

	/**
	 * Find a stand by using ID number
	 * 
	 * @param id
	 * @return Stand
	 */
	public Stand findStandByID(int id) {
		return em.find(Stand.class, id);
	}

	/**
	 * Returns all stands
	 * 
	 * @return List<Stand>
	 */
	public List<Stand> getAllStands() {
		
		String jpql = "SELECT s FROM Stand s";
		
	    TypedQuery<Stand> query = em.createQuery(jpql, Stand.class);
	    return query.getResultList();
	}
}
