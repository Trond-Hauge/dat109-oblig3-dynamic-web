package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vote.Vote;
import vote.VoteDAO;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private VoteDAO dao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String standId = request.getParameter("id");
		
		if(standId == null) {
			
			request.getRequestDispatcher("WEB-INF/ChooseStand.jsp").forward(request, response);
		}
		else {
			
			request.getRequestDispatcher("WEB-INF/Voting.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String phone = request.getParameter("phone");
		String standId = request.getParameter("standId"); //Hidden parameter
		int points = Integer.parseInt(request.getParameter("points"));
		
		//TODO implement sessions for phonenumbers?
		
		Vote vote = new Vote(phone, standId);
		
		Vote previous = dao.findVote(vote);
		
		if(previous != null) {
			
			dao.remove(previous);
		}
		vote.setPoints(points);
		dao.add(vote);
		
		request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
	}
}
