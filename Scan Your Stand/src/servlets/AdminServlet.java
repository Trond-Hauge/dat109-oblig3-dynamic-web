package servlets;

import java.io.IOException;
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
import utils.AdminUtils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ExhibitionDAO exhibitionDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean loggedIn = AdminUtils.isLoggedIn(request);
		
		if(loggedIn) {
			List<Exhibition> exhibitions = exhibitionDao.getAllExhibitions();
			request.setAttribute("exhibitions", exhibitions);
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("login-admin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(!AdminUtils.isLoggedIn(request)) {
			response.sendRedirect("login-admin");
			
		}else {
		
			int exhibitionid = Integer.parseInt(request.getParameter("exhibitionid"));
			Exhibition exhibition = exhibitionDao.findExhibitionById(exhibitionid);
			
			String operation = request.getParameter("operation");
			
			if(operation != null) {
				
				if(operation.equalsIgnoreCase("start")) {
					exhibition.setStart(LocalDateTime.now());
					exhibition.setActive(true);
					response.sendRedirect("admin"); //Continue doing operations
				}
				else if(operation.equalsIgnoreCase("stop")){
					exhibition.setStop(LocalDateTime.now());
					exhibition.setActive(false);
					response.sendRedirect("admin"); //Continue doing operations
				}
				else if(operation.equalsIgnoreCase("logout")){
					AdminUtils.logOut(request);
					response.sendRedirect("login-admin");
				}
				else if(operation.equalsIgnoreCase("administrate")) {
					request.getSession().setAttribute("exhibition", exhibition);
					response.sendRedirect("manageExhibition");
				}
				else {
					response.sendRedirect("admin"); //Should not happen
				}
			}
			else {
				response.sendRedirect("admin"); //Should not happen
			}
		}
	}
}
