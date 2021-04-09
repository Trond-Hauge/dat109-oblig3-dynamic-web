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
import utils.AdminOperation;
import utils.AdminUtils;

@WebServlet("/admin-landing")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ExhibitionDAO exhibitionDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean loggedIn = AdminUtils.isLoggedIn(request);
		
		if(loggedIn) {
			List<Exhibition> exhibitions = exhibitionDao.getAllExhibitions();
			request.setAttribute("startStr", AdminOperation.START.toString());
			request.setAttribute("stopStr", AdminOperation.STOP.toString());
			request.setAttribute("administrateStr", AdminOperation.ADMINISTRATE.toString());
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
			
			String operationStr = request.getParameter("operation");
			AdminOperation operation = operationStr == null || operationStr.isBlank() ? null : AdminOperation.getOperation(operationStr);

			if(operation != null && exhibition != null) {
				
				switch(operation) {
					
					case START:
						exhibition.setStart(LocalDateTime.now());
						exhibition.setActive(true);
						exhibitionDao.updateExhibition(exhibition);
						response.sendRedirect("admin-landing");
						break;
						
					case STOP:
						exhibition.setStop(LocalDateTime.now());
						exhibition.setActive(false);
						exhibitionDao.updateExhibition(exhibition);
						response.sendRedirect("admin-landing");
						break;
						
					case ADMINISTRATE: 
						request.getSession().setAttribute("exhibition", exhibition);
						response.sendRedirect("manageExhibition");
						break;
				
				}
				
			}	
			else {
				System.out.println("-- SOMETHING WRONG --");
				response.sendRedirect("admin-landing"); //Should not happen
			}
			
		}
		
	}
	
}
