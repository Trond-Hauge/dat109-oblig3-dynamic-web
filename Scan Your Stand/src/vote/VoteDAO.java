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

		String jpql = "SELECT v FROM Vote v";
		TypedQuery<Vote> query = em.createQuery(jpql, Vote.class);

		return query.getResultList();

	}

	/**
	 * Returns a Vote object or null
	 * 
	 * @param Vote
	 * @return Vote
	 */
	public Vote findVote(VoteID voteID) {
		return em.find(Vote.class, voteID);
	}

	/**
	 * Removes a previous vote
	 * 
	 * @param Vote previous
	 */
	public void remove(Vote previous) {
		previous = em.merge(previous);
		em.remove(previous);
	}

	/**
	 * Adds a vote 
	 * 
	 * @param vote
	 */
	public void add(Vote vote) {
		em.persist(vote);
	}

}
