package servlets;

import utils.BCrypt;
import utils.Constants;
import utils.Validator;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import exhibition.Exhibition;
import exhibition.ExhibitionDAO;
import project.Project;
import project.ProjectDAO;
import vote.Vote;
import vote.VoteDAO;
import vote.VoteID;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private VoteDAO voteDAO;
	
	@EJB
	private ProjectDAO projectDAO;
	
	@EJB
	private ExhibitionDAO exhibitionDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String changeVote = request.getParameter("changeVote");
		
		if(changeVote != null && request.getSession().getAttribute("previousVote") != null) {
			
			Vote previousVote = (Vote) request.getSession().getAttribute("previousVote");
			Project project = projectDAO.findProjectByID(previousVote.getProjectNumber());
			
			if(!project.getExhibition().isActive()) {
				List<Exhibition> exhibitions = exhibitionDAO.getAllActiveExhibitions();
				request.setAttribute("exhibitions", exhibitions);
				request.getRequestDispatcher("WEB-INF/choose-expo-and-stand.jsp").forward(request, response);
			}
			
			String points = previousVote.getPoints() + "";
			String revote = "true";
			request.setAttribute("points", points);
			request.setAttribute("revote", revote);
			request.setAttribute("project", project);
			request.getRequestDispatcher("WEB-INF/project.jsp").forward(request, response);
			
		}
		else {
			
			String projectId = request.getParameter("projectnr");
			Project project = projectDAO.findProjectByID(projectId);
			Exhibition exhibiton = project == null ? null : project.getExhibition();
			
			int chosenExhibitionId = -1; 
			try {
				chosenExhibitionId = Integer.parseInt(request.getParameter("exhibitionid"));
			}
			catch(NumberFormatException e) {}
			
			Exhibition chosenExhibition = chosenExhibitionId == -1 ? null : exhibitionDAO.findExhibitionById(chosenExhibitionId);
			
			if(project == null || exhibiton == null || !exhibiton.isActive()) { 
				
				List<Exhibition> exhibitions = exhibitionDAO.getAllActiveExhibitions();
		
				if(chosenExhibition != null && chosenExhibition.isActive()) {
					request.setAttribute("exhibition", chosenExhibition);
					List<Project> projects = chosenExhibition.getProjects();
					request.setAttribute("projects", projects);
				}
				
				request.setAttribute("exhibitions", exhibitions);
				request.getRequestDispatcher("WEB-INF/choose-expo-and-stand.jsp").forward(request, response);
			}
			else {
				String revote = request.getParameter("revote");
				String points = request.getParameter("points");
				request.setAttribute("points", points);
				request.setAttribute("revote", revote);
				request.setAttribute("project", project);
				request.getRequestDispatcher("WEB-INF/project.jsp").forward(request, response);
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String phoneNumber = request.getParameter("number");
		String projectId = request.getParameter("projectid"); //Hidden parameter
		Project project = null;
		Exhibition exhibiton = null;
		int points = Integer.parseInt(request.getParameter("points"));
		
		try {
			project = projectDAO.findProjectByID(projectId);
			exhibiton = project.getExhibition();
		}
		catch(Exception e) {}
		
		if(project != null && exhibiton != null && exhibiton.isActive() && Validator.validPhoneNumber(phoneNumber)) {
			
			HttpSession sesjon = request.getSession(false);
	        if (sesjon != null && sesjon.getAttribute("phoneNumber") == null) {
	            sesjon.invalidate();
	        }
	        sesjon = request.getSession(true);
	        sesjon.setMaxInactiveInterval(Constants.USER_MAX_TIME_INACTIVE);
			sesjon.setAttribute("phoneNumber", phoneNumber); //Can now be remembered the next time the spectator votes (prefilled)
			sesjon.setAttribute("lastVotedProjectId", projectId); // Session remembers last voted project, this allows confirmation page to properly display in more cases
	        
			VoteID voteId = new VoteID(phoneNumber, projectId);
			Vote previous = voteDAO.findVote(voteId);
			String revoteApproved = request.getParameter("revote");
			
			if(previous != null && revoteApproved == null) {
				response.sendRedirect("vote?revote=true&projectnr=" + projectId + "&points=" + points);
			}
			else {
				if(previous != null && revoteApproved != null) {
					voteDAO.remove(previous);
				}
				
				Vote vote = new Vote(phoneNumber, projectId, points);
				voteDAO.add(vote);
				
				request.getSession().setAttribute("previousVote", vote);
				response.sendRedirect("confirmation");
			}
		}
		else {
			response.sendRedirect("vote");
		}
		
	}
	
}
