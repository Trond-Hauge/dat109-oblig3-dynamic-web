package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhibition.Exhibition;
import project.Project;
import project.ProjectDAO;
import utils.AdminUtils;
import utils.TimeUtils;

@WebServlet("/manageExhibition")
public class ManageExhibitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProjectDAO projectDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(AdminUtils.isLoggedIn(request)) {
			
			Exhibition exhibition = (Exhibition)request.getSession().getAttribute("exhibition");
			
			if(exhibition == null) {
				response.sendRedirect("admin");
			}
			else {
				String expoName = exhibition.getName();
				request.setAttribute("expoName", expoName);
				
				String timestampStringStart = exhibition.timeStringStart();
				request.setAttribute("startTimeString", timestampStringStart);
				
				String timestampStringStop = exhibition.timeStringStop();
				request.setAttribute("stopTimeString", timestampStringStop);
				
				List<Project> projects = exhibition.getProjects();
				request.setAttribute("projects", projects);
				
				request.getRequestDispatcher("WEB-INF/manage-exhibition.jsp").forward(request, response);
			}
		}
		else {
			response.sendRedirect("login-admin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(!AdminUtils.isLoggedIn(request)) {
			response.sendRedirect("login-admin");
		}
		else {
			
			String operation = request.getParameter("operation");
			
			if(operation != null) {
			
				if(operation.equalsIgnoreCase("add") || operation.equalsIgnoreCase("remove")) {
					
					String projectNumber = request.getParameter("projectNumber");
					
					if(projectNumber != null) {
						
						if(operation.equalsIgnoreCase("add")) {
							
							String projectName = request.getParameter("projectName");
							
							if(projectName != null) {
								
								Project project = new Project(projectNumber, projectName);
								projectDao.addProject(project);
							}
						}
						else {
							
							Project project = projectDao.findProjectByID(projectNumber);
							
							if(project != null) {
			
								projectDao.removeProject(project);
							}
						}
					}
				}
				
				else if(operation.equalsIgnoreCase("updateStart") || operation.equalsIgnoreCase("updateStop")) {
					
					Exhibition exhibition = (Exhibition)request.getSession().getAttribute("exhibition");
					
					if(exhibition != null) {
						
						String date = request.getParameter("date");
						String time = request.getParameter("time");
						
						boolean dateValid = TimeUtils.validDate(date);
						boolean timeValid = TimeUtils.validTime(time);
						
						if(dateValid && timeValid) {
							
							if(operation.equalsIgnoreCase("start")) {
								LocalDateTime newStart = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
								exhibition.setStart(newStart);
							}
							else {
								LocalDateTime newStop = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
								exhibition.setStop(newStop);;
							}
						}	
					}
				}
				else if(operation.equalsIgnoreCase("import")) {
					//TODO
				}	
			}
			response.sendRedirect("manageExhibition"); //Make more updates
		}
	}
}
