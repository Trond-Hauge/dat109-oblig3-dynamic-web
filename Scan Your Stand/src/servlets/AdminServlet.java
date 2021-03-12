package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.AdminUtils;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isLoggedIn = AdminUtils.isLoggedIn(request);
		
		if(isLoggedIn) {
			request.getRequestDispatcher("JSP/admin.jsp");
		}
		else {
			request.getRequestDispatcher("JSP/adminLogin.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
