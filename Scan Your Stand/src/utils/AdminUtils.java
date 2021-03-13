package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminUtils {
	
	public static boolean isLoggedIn(HttpServletRequest request) {
		
		return request.getSession() != null && request.getSession().getAttribute("loggedIn") != null;
		
	}
	
	
	public static void logIn(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		session = request.getSession(true);
		session.setAttribute("loggedIn", "true");
		session.setMaxInactiveInterval(600);
	
	}

}
