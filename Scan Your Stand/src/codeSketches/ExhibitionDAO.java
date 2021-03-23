package codeSketches;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import codeSketches.startStopUtils.Exhibition;

@Stateless
public class ExhibitionDAO {

	@PersistenceContext(name = "scanyourstandUP")
	private EntityManager em;

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
}
