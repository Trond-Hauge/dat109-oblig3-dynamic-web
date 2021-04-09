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
import exhibition.ExhibitionDAO;
import project.Project;
import project.ProjectDAO;
import utils.AdminUtils;
import utils.ExhibitionOperation;
import utils.TimeUtils;
import utils.Validator;

@WebServlet("/manageExhibition")
public class ManageExhibitionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProjectDAO projectDAO;
	
	@EJB
	private ExhibitionDAO exhibitionDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(AdminUtils.isLoggedIn(request)) {
			
			Exhibition exhibition = (Exhibition)request.getSession().getAttribute("exhibition");
			
			if(exhibition == null) {
				response.sendRedirect("admin-landing");
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
				
				// Strings that will be displayed on operation-buttons and later used to identify the specific operation
				request.setAttribute("addStr", ExhibitionOperation.ADD.toString());
				request.setAttribute("removeStr", ExhibitionOperation.REMOVE.toString());
				request.setAttribute("updateStartStr", ExhibitionOperation.UPDATE_START.toString());
				request.setAttribute("updateStopStr", ExhibitionOperation.UPDATE_STOP.toString());
				request.setAttribute("importStr", ExhibitionOperation.IMPORT.toString());
				
				request.getRequestDispatcher("WEB-INF/admin-expo-config.jsp").forward(request, response);
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
			
			String operationStr = request.getParameter("operation");
			ExhibitionOperation operation = operationStr == null || operationStr.isBlank() ? null : ExhibitionOperation.getOperation(operationStr);
			
			if(operation != null) {
				
				String projectNumber = request.getParameter("projectNumber");
				Exhibition exhibition = (Exhibition) request.getSession().getAttribute("exhibition");
				
				switch(operation) {
				
					case ADD:
						
						String projectName = request.getParameter("projectName");
						
						if(Validator.validProjectName(projectName) && Validator.validProjectNumber(projectNumber)) {
							Project newProject = new Project(projectNumber,projectName);
							Project existingPrtoject = projectDAO.findProjectByID(projectNumber);
							
							if(existingPrtoject == null && exhibition != null) {
								newProject.setExhibition(exhibition);
								projectDAO.addProject(newProject);
							}
						}
						
						break;
						
					case REMOVE:
						
						Project project = projectDAO.findProjectByID(projectNumber);
						
						if(project != null) {
							projectDAO.removeProject(project);
						}
						
						break;
						
					case UPDATE_START:
						
						if(exhibition != null) {
							String date = request.getParameter("date");
							String time = request.getParameter("time");
							
							boolean dateValid = TimeUtils.validDate(date);
							boolean timeValid = TimeUtils.validTime(time);
							
							if(dateValid && timeValid) {
								LocalDateTime newStart = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
								exhibition.setStart(newStart);
								exhibitionDAO.updateExhibition(exhibition);
							}
							
						}
						
						break;
						
					case UPDATE_STOP:
						
						if(exhibition != null) {
							String date = request.getParameter("date");
							String time = request.getParameter("time");
							
							boolean dateValid = TimeUtils.validDate(date);
							boolean timeValid = TimeUtils.validTime(time);
							
							if(dateValid && timeValid) {
								LocalDateTime newStart = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
								exhibition.setStop(newStart);
								exhibitionDAO.updateExhibition(exhibition);
							}
							
						}
						
						break;
						
					case IMPORT:
						
						// TODO
						
						break;
			
				}
			}
			
			response.sendRedirect("manageExhibition"); //Make more updates
			
		}
		
	}
	
}
