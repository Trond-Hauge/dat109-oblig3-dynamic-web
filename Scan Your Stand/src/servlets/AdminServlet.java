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
		
		//Adding or removing projects from the exhibition
		else {
			
			String projectNumber = request.getParameter("projectnr");
			
			if(operation.equalsIgnoreCase("add")) {
				
				String projectName = request.getParameter("projectname");
				Project project = new Project(projectNumber, projectName);
				projectDao.addProject(project);
				exhibitionDao.addProject(project);
			}
			else if(operation.equalsIgnoreCase("remove")) {
				
				Project project = projectDao.findProjectByID(projectNumber);
				exhibitionDao.removeProject(project);
				
				//Fjerne prosjektet fullstendig?
				//projectDao.remove(project);
			}
		}
		response.sendRedirect("admin");
	}
}
