package vote;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Defines the Data Access Object for Vote
 * 
 * @author Ida
 *
 */
@Stateless
public class VoteDAO {
	
	@PersistenceContext(name = "scanyourstandUP")
	private EntityManager em;

	
	/**
	 * Returns all votes
	 * 
	 * @return List<Vote>
	 */
	public List<Vote> getAllVotes() {
		
		String jpql = "SELECT v from VOTE v";	
		TypedQuery<Vote> query = em.createQuery(jpql, Vote.class);
		
	    return query.getResultList();
		
	}

}
