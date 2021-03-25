package exhibition;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ExhibitionDAO {

	@PersistenceContext(name = "scanyourstandUP")
	private EntityManager em;

	/**
	 * Finds a specific exhibition by its id
	 * 
	 * @param int id
	 * @return Exhibition
	 */
	public Exhibition findExhibitionById(int id) {
		return em.find(Exhibition.class, id);
	}
	
	/**
	 * Returns all exhibitions
	 * 
	 * @return List<Exhibition>
	 */
	public List<Exhibition> getAllExhibitions() {

		return em.createQuery("SELECT e FROM Exhibition e", Exhibition.class).getResultList();

	}

	/**
	 * Returns all active exhibitions
	 * 
	 * @return List<Exhibition>
	 */
	public List<Exhibition> getAllActiveExhibitions() {

		String jpql = "SELECT e FROM Exhibition e WHERE e.isActive='true'";

		return em.createQuery(jpql, Exhibition.class).getResultList();
	}

	/**
	 * Add an exhibition
	 * 
	 * @param exhibition
	 */
	public void addExhibition(Exhibition exhibition) {
		em.persist(exhibition);
	}

	/**
	 * Remove an exhibition
	 * 
	 * @param exhibition
	 */
	public void removeExhibition(Exhibition exhibition) {
		exhibition = em.merge(exhibition);
		em.remove(exhibition);
	}
	
	/**
	 * Updates and exhibition
	 * 
	 * @param exhibition
	 */
	public void updateExhibition(Exhibition exhibition) {
		em.merge(exhibition);
	}
}