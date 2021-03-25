package qr;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Project;
import project.ProjectDAO;

/**
 * Servlet implementation class QrInitServlet
 */
@WebServlet("/qr")
public class QrInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private ProjectDAO dao;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Project> projects = dao.getAllProjects();
		MyQr.createQrCodesForProjects(projects);

		response.getWriter().append("QR codes generated and stored in: " + System.getProperty("user.home"));
	}
}
