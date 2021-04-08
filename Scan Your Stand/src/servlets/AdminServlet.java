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
import utils.Constants;

@WebServlet("/admin-landing")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ExhibitionDAO exhibitionDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean loggedIn = AdminUtils.isLoggedIn(request);
		
		if(loggedIn) {
			List<Exhibition> exhibitions = exhibitionDao.getAllExhibitions();
			request.setAttribute("startStr", Constants.START_STRING);
			request.setAttribute("stopStr", Constants.STOP_STRING);
			request.setAttribute("administrateStr", Constants.ADMINISTRATE_STRING);
			request.setAttribute("exhibitions", exhibitions);
			request.getRequestDispatcher("WEB-INF/admin-landing.jsp").forward(request, response);
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
			
			if(operation != null && exhibition != null) {
				
				if(operation.equalsIgnoreCase(Constants.START_STRING)) {
					exhibition.setStart(LocalDateTime.now());
					exhibition.setActive(true);
					exhibitionDao.updateExhibition(exhibition);
				}
				else if(operation.equalsIgnoreCase(Constants.STOP_STRING)){
					exhibition.setStop(LocalDateTime.now());
					exhibition.setActive(false);
					exhibitionDao.updateExhibition(exhibition);
					System.out.println("-- TEST! --");
				}
				else if(operation.equalsIgnoreCase(Constants.ADMINISTRATE_STRING)) {
					request.getSession().setAttribute("exhibition", exhibition);
					response.sendRedirect("manageExhibition");
					
				}
				
				response.sendRedirect("admin-landing"); 
			}
			else {
				System.out.println("-- SOMETHING WRONG --");
				response.sendRedirect("admin-landing"); //Should not happen
			}
			
		}
		
	}
	
}
