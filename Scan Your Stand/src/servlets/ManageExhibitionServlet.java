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
import project.Project;
import project.ProjectDAO;
import utils.AdminUtils;

@WebServlet("/manageExhibition")
public class ManageExhibitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@EJB
	private ExhibitionDAO exhibitionDao;
	
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
				
				List<Exhibition> expos = exhibitionDao.getAllExhibitions();
				request.setAttribute("expos", expos);
				
				request.getRequestDispatcher("WEB-INF/manage-exhibition.jsp").forward(request, response);
			}
		}
		else {
			response.sendRedirect("login-admin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
							Project project = new Project(projectNumber, projectName);
							projectDao.addProject(project);
							exhibitionDao.addProject(project);
						}
						else {
							Project project = projectDao.findProjectByID(projectNumber);
							if(project != null) {
								exhibitionDao.removeProject(project);
								//projectDao.remove(project); Remove project completely?
							}
						}
					}
				}
				
				else if(operation.equalsIgnoreCase("updateStart") || operation.equalsIgnoreCase("updateStop")) {
					
					Exhibition exhibition = (Exhibition)request.getSession().getAttribute("exhibition");
					
					if(exhibition != null) {
						
						int date = 0;
						int hour = 0;
						
						try {
							date = Integer.parseInt(request.getParameter("date"));
							hour = Integer.parseInt(request.getParameter("hour"));
							
						}catch(NumberFormatException e) {
							response.sendRedirect("manageExhibition"); //Did not choose date/time
						}
						
						if(operation.equalsIgnoreCase("start")) {
							LocalDateTime newStart = LocalDateTime.of(exhibition.getStart().getYear(),
									exhibition.getStart().getMonthValue(),
									date, hour, 0);
							exhibition.setStart(newStart);
						}
						else {
							LocalDateTime newStop = LocalDateTime.of(exhibition.getStop().getYear(),
									exhibition.getStop().getMonthValue(),
									date, hour, 0);
							exhibition.setStop(newStop);;
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
