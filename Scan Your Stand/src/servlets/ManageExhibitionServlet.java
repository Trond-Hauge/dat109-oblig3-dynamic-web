package servlets;

import java.io.IOException;
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

@WebServlet("/manageExhibition")
public class ManageExhibitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@EJB
	private ExhibitionDAO exhibitionDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(AdminUtils.isLoggedIn(request)) {
			
			Exhibition exhibition = (Exhibition)request.getAttribute("exhibition");
			
			String expoName = exhibition.getName();
			request.setAttribute("expoName", expoName);
			
			String timestampStringStart = exhibition.timeStringStart();
			request.setAttribute("startTimeString", timestampStringStart);
			
			String timestampStringStop = exhibition.timeStringStop();
			request.setAttribute("stopTimeString", timestampStringStop);
			
			List<Exhibition> expos = exhibitionDao.getAllExhibitions();
			request.setAttribute("expos", expos);
			
			request.getRequestDispatcher("WEB-INF/manage-exhibition.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("login-admin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO
	}
}
