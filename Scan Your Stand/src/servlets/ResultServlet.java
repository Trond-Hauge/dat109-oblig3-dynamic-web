package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stand.Stand;
import vote.Vote;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VoteDAO voteDAO;
	
	@EJB
	private StandDAO standDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Vote> votes = voteDAO.getAllVotes();
		List<Stand> stands = standDAO.getAllStands();
		
		HashMap<Stand,Integer> result = new HashMap<Stand,Integer>();
		
		stands.forEach(s -> {
			
			
			
		});
		
		
		request.getRequestDispatcher("JSP/Result.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("result");
		
	}

}
