package servlets;

import utils.Constants;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		HttpSession sesjon = request.getSession(false);
        if (sesjon != null && sesjon.getAttribute("phoneNumber") == null) {
            sesjon.invalidate();
        }
        sesjon = request.getSession(true);
        sesjon.setMaxInactiveInterval(Constants.USER_MAX_TIME_INACTIVE);
		sesjon.setAttribute("phoneNumber", phone); //Can now be remembered the next time the spectator votes (prefilled)
        
		Vote vote = new Vote(phone, standId);
		
		Vote previous = dao.findVote(vote);
		
		if(previous != null) {
			
			dao.remove(previous);
		}
		vote.setPoints(points);
		dao.add(vote);
		
		request.getRequestDispatcher("WEB-INF/Confirmation.jsp").forward(request, response);
	}
}
