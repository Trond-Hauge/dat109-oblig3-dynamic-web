package project;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import exhibition.Exhibition;

/**
 * Defines the Data Access Object for Project
 * 
 * @author Ida
 *
 */
@Stateless
public class ProjectDAO {

	@PersistenceContext(name = "scanyourstandUP")
	private EntityManager em;

	
	/**
	 * Find a project by using project number
	 * 
	 * @param String id
	 * @return Project
	 */
	public Project findProjectByID(String projectNumber) {
		return projectNumber == null ? null : em.find(Project.class, projectNumber);
	}

	/**
	 * Returns all projects
	 * 
	 * @return List<Project>
	 */
	public List<Project> getAllProjects() {
		
		String jpql = "SELECT p FROM Project p";
		
	    TypedQuery<Project> query = em.createQuery(jpql, Project.class);
	    return query.getResultList();
	}
	
	/**
	 * Adds a project
	 * 
	 * @param project
	 */
	public void addProject(Project project) {
		em.persist(project);
	}
	
	/**
	 * Remove a project
	 * 
	 * @param project
	 */
	public void removeExhibition(Project project) {
		project = em.merge(project);
		em.remove(project);
	}
}
