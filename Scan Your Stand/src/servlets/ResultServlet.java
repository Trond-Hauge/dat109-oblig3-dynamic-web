package servlets;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Vote> votes = voteDAO.getAllVotes();
		List<Stand> stands = standDAO.getAllStands();
		
		request.getSession();
		
		HashMap<String,Integer> resultMap = new HashMap<String,Integer>();
		
		stands.forEach(s -> {
			
			int voteSum = VoteCalculator.calculateVotes(s, votes);
			resultMap.put(s.getStandName(), voteSum);
			
		});
		
		request.setAttribute("resultMap", resultMap);
		
		request.getRequestDispatcher("JSP/Result.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("result");
		
	}

}
