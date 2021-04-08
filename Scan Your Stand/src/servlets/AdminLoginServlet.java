package servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import admin.Admin;
import admin.AdminDAO;
import utils.AdminUtils;
import utils.BCrypt;

@WebServlet("/login-admin")
public class AdminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	AdminDAO adminDAO;

	// Checks if user is logged in as admin, if so forwards to the admin web page, otherwise forwards back to the login page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		AdminUtils.logOut(request);
		request.getRequestDispatcher("WEB-INF/login-admin.jsp").forward(request, response);
	}

	// Checks login data to see if user has correctly entered admin username and password, if successful the user is logged in, redirects to AdminServlet doGet()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String error = "";

		Admin admin = adminDAO.findAdmin(username);

		// Upon successful login, user is logged in, otherwise appropriate error message is set. BCrypt is used to hash and check password.
		if(admin == null) {
			error = "?error=Admin bruker ikke funnet";
		}
		else if(BCrypt.checkpw(password, admin.getHashedPassword())) {
			AdminUtils.logIn(request);
			Cookie testCookie = new Cookie("tesCookie", "value");
			response.addCookie(testCookie);
		}
		else {
			error = "?error=Feil passord oppgitt";
		}
		response.sendRedirect("admin-landing" + error);
	}
}
