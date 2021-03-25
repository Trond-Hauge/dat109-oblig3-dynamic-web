package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhibition.Exhibition;
import exhibition.ExhibitionDAO;
import utils.AdminUtils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ExhibitionDAO dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean loggedIn = AdminUtils.isLoggedIn(request);
		
		if(loggedIn) {
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("WEB-INF/login-admin.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int exhibitionid = Integer.parseInt(request.getParameter("exhibitionid"));
		Exhibition exhibition = dao.findExhibitionById(exhibitionid);
		
		String operation = request.getParameter("operation");
		
		if(operation.equalsIgnoreCase("start")) {
			exhibition.setStart(LocalDateTime.now());
			exhibition.setActive(true);
		}
		else if(operation.equalsIgnoreCase("stop")){
			exhibition.setStop(LocalDateTime.now());
			exhibition.setActive(false);
		}
		response.sendRedirect("admin");
	}
}
