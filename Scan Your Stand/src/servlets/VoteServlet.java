package servlets;

import utils.Constants;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.Project;
import project.ProjectDAO;
import vote.Vote;
import vote.VoteDAO;
import vote.VoteID;

/**
 * 
 * @author anders
 * 
 * Servlet for voting
 *
 */

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private VoteDAO voteDAO;
	
	@EJB
	private ProjectDAO projectDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String projectId = request.getParameter("projectnr");
		Project project = null;
	
		project = projectDAO.findProjectByID(projectId);	
		
		if(project == null) {
			
			List<Project> projects = projectDAO.getAllProjects();
			//testing
			//projects.stream().map(p -> p.getProjectName()).forEach(System.out::println);
			
			request.setAttribute("projects", projects);
			request.getRequestDispatcher("WEB-INF/choose-expo-and-stand.jsp").forward(request, response);
		}
		else {
			
			request.setAttribute("project", project);
			request.getRequestDispatcher("WEB-INF/project.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String phone = request.getParameter("number");
		String projectId = request.getParameter("projectid"); //Hidden parameter
		int points = Integer.parseInt(request.getParameter("points"));
		
		HttpSession sesjon = request.getSession(false);
        if (sesjon != null && sesjon.getAttribute("phoneNumber") == null) {
            sesjon.invalidate();
        }
        sesjon = request.getSession(true);
        sesjon.setMaxInactiveInterval(Constants.USER_MAX_TIME_INACTIVE);
		sesjon.setAttribute("phoneNumber", phone); //Can now be remembered the next time the spectator votes (prefilled)
		sesjon.setAttribute("lastVotedProjectId", projectId); // Session remembers lasted voted project, this allows confirmation page to properly display in more cases
        
		VoteID voteId = new VoteID(phone, projectId);
		Vote previous = voteDAO.findVote(voteId);
		
		if(previous != null) {
			
			voteDAO.remove(previous);
		}
		Vote vote = new Vote(phone, projectId, points);
		voteDAO.add(vote);
		
		response.sendRedirect("confirmation");
	}
}
