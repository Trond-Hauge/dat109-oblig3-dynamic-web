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
import stand.StandDAO;
import utils.VoteCalculator;
import vote.Vote;
import vote.VoteDAO;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VoteDAO voteDAO;
	
	@EJB
	private StandDAO standDAO;
	
	// Readies data before forwarding to result.jsp
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Vote> votes = voteDAO.getAllVotes();
		List<Stand> stands = standDAO.getAllStands();
		
		// Hashmap where each Stand is stored with their corresponding voter score
		HashMap<String,Integer> resultMap = new HashMap<String,Integer>();
		
		// Calculates each voter score for every stand and puts the values into the hashmap
		stands.forEach(s -> {
			
			int voteSum = VoteCalculator.calculateVotes(s, votes);
			resultMap.put(s.getStandName(), voteSum);
			
		});
		
		// Sets the hashmap as a request attribute
		request.setAttribute("resultMap", resultMap);
		
		// Forwards to result.jsp
		request.getRequestDispatcher("WEB-INF/result.jsp").forward(request, response);
		
	}

	// Redirects to ResultServlet doGet()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("result");
		
	}

}
