package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhibition.Exhibition;
import exhibition.ExhibitionDAO;
import project.Project;
import project.ProjectDAO;
import utils.VoteUtils;
import vote.Vote;
import vote.VoteDAO;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VoteDAO voteDAO;
	
	@EJB
	private ProjectDAO projectDAO;
	
	@EJB
	private ExhibitionDAO exhibitionDAO;
	
	// Readies data before forwarding to result.jsp
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Exhibition exhibition = null;
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			exhibition = exhibitionDAO.findExhibitionById(id);
		}
		catch(Exception e) {}
		
		if(exhibition == null) {
			List<Exhibition> exhibitions = exhibitionDAO.getAllExhibitions();
			request.setAttribute("exhibitions", exhibitions);
			request.getRequestDispatcher("WEB-INF/view-results.jsp").forward(request, response);
		}
		else {
			List<Vote> votes = voteDAO.getAllVotes();
			List<Project> projects = exhibition.getProjects();
			
			// Gets the sorted project score map
			Map<String,Integer> resultMap = VoteUtils.getSortedProjectScoreMap(projects, votes);
			
			// Sets the sorted map as a request attribute
			request.setAttribute("resultMap", resultMap);
			
			// Forwards to result.jsp
			request.getRequestDispatcher("WEB-INF/view-results.jsp").forward(request, response);	
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		response.sendRedirect("result?id=" + id);
		
	}
}
