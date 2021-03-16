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
import utils.VoteUtils;
import vote.Vote;
import vote.VoteDAO;

@WebServlet("/myvotes")
public class MyVotesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VoteDAO voteDAO;
	
	@EJB
	private StandDAO standDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String phone = request.getParameter("phone");
		List<Vote> votes = voteDAO.getAllVotes();
		List<Stand> stands = standDAO.getAllStands();
		HashMap<String,Integer> standsAndPoints = VoteUtils.getUserVotes(phone, votes, stands);

		if(standsAndPoints == null) {
			request.getRequestDispatcher("WEB-INF/landing.jsp").forward(request, response);
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
