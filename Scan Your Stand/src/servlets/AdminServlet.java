package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
import utils.AdminUtils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ExhibitionDAO exhibitionDao;
	
	@EJB
	private ProjectDAO projectDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean loggedIn = AdminUtils.isLoggedIn(request);
		
		if(loggedIn) {
			List<Exhibition> expos = exhibitionDao.getAllExhibitions();
			request.setAttribute("expos", expos);
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("WEB-INF/login-admin.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int exhibitionid = Integer.parseInt(request.getParameter("exhibitionid"));
		Exhibition exhibition = exhibitionDao.findExhibitionById(exhibitionid);
		
		String operation = request.getParameter("operation");
		
		if(operation.equalsIgnoreCase("start")) {
			exhibition.setStart(LocalDateTime.now());
			exhibition.setActive(true);
		}
		else if(operation.equalsIgnoreCase("stop")){
			exhibition.setStop(LocalDateTime.now());
			exhibition.setActive(false);
		}
		
		else {
			
		}
		response.sendRedirect("admin");
	}
}
