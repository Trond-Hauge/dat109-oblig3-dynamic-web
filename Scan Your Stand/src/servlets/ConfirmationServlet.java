package servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.Project;
import project.ProjectDAO;
import vote.Vote;
import vote.VoteDAO;

@WebServlet("/confirmation")
public class ConfirmationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VoteDAO voteDAO;
	
	@EJB
	private ProjectDAO projectDAO;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String projectId = request.getParameter("id");
		String phone = (String) request.getSession().getAttribute("phone");
		
		if(projectId == null || phone == null) {
			response.sendRedirect("result");
		}
		else {
			
			Vote vote = null;
			Project project = null;
			
			try {
				vote = voteDAO.findVote(new Vote(phone, projectId));
				project = projectDAO.findProjectByID(projectId);
			}
			catch(Exception e) {}
			
			if(vote == null || project == null) {
				response.sendRedirect("result");
			}
			else {
				request.setAttribute("project", project);
				request.setAttribute("vote", vote);
				request.getRequestDispatcher("WEB-INF/vote-success").forward(request, response);
			}
	
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.sendRedirect("result");
		
	}

}