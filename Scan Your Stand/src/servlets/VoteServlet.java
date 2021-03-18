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
		
		String projectId = request.getParameter("id");
		Project project = null;
		
		try {
			project = projectDAO.findProjectByID(projectId);
		}
		catch(Exception e) {}
		
		if(project == null) {
			
			List<Project> projects = projectDAO.getAllProjects();
			request.setAttribute("projects", projects);
			request.getRequestDispatcher("WEB-INF/chooseStand.jsp").forward(request, response);
		}
		else {
			
			request.setAttribute("project", project);
			request.getRequestDispatcher("WEB-INF/voting.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String phone = request.getParameter("phone");
		String projectId = request.getParameter("projectId"); //Hidden parameter
		int points = Integer.parseInt(request.getParameter("points"));
		
		HttpSession sesjon = request.getSession(false);
        if (sesjon != null && sesjon.getAttribute("phoneNumber") == null) {
            sesjon.invalidate();
        }
        sesjon = request.getSession(true);
        sesjon.setMaxInactiveInterval(Constants.USER_MAX_TIME_INACTIVE);
		sesjon.setAttribute("phoneNumber", phone); //Can now be remembered the next time the spectator votes (prefilled)
        
		Vote vote = new Vote(phone, projectId);
		
		Vote previous = voteDAO.findVote(vote);
		
		if(previous != null) {
			
			voteDAO.remove(previous);
		}
		vote.setPoints(points);
		voteDAO.add(vote);
		
		request.getRequestDispatcher("WEB-INF/confirmation.jsp").forward(request, response);
	}
}
