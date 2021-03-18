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
import project.Project;
import project.ProjectDAO;
import utils.VoteUtils;
import vote.Vote;
import vote.VoteDAO;

@WebServlet("/myvotes")
public class MyVotesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VoteDAO voteDAO;
	
	@EJB
	private ProjectDAO projectDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String phone = request.getParameter("phone");
		List<Vote> votes = voteDAO.getAllVotes();
		List<Project> projects = projectDAO.getAllProjects();
		Map<String,Integer> projectsAndPoints = VoteUtils.getUserVotes(phone, votes, projects);

		if(projectsAndPoints == null) {
			request.getRequestDispatcher("WEB-INF/project.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("WEB-INF/myvotes.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String phone = (String) request.getSession().getAttribute("phoneNumber");
		if(phone == null) {
			phone = "";
		}
		else {
			phone = "?phone=" + phone;
		}
		
		response.sendRedirect("myvotes" + phone);
		
	}

}
