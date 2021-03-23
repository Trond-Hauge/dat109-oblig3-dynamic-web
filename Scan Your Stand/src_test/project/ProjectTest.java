package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProjectTest {

	@Test
	public void canSaveProject() {
		Project project = new Project("D01", "Scan Your Stand");
		ProjectDAO dao = new ProjectDAO();
		dao.addProject(project);
		
		Project returnedProject = dao.findProjectByID(project.getProjectNumber());
		assertEquals(returnedProject, project);
	}

}
