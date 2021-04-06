package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminUtils {
	
	/**
	 * Checks if the user is logged in as admin
	 * 
	 * @author Oliver
	 * @param request
	 * @return true if user is logged in as admin in the session, false if not
	 */
	public static boolean isLoggedIn(HttpServletRequest request) {
		
		return request.getSession() != null && request.getSession().getAttribute("loggedIn") != null;
		
	}
	
	/**
	 * Logs the user in as admin
	 * 
	 * @author Oliver
	 * @param request
	 */
	public static void logIn(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		// Adds the loggedIn attribute to the session and sets a max inactive timer, after which the user will no longer be logged in.
		session = request.getSession(true);
		session.setAttribute("loggedIn", "true");
		session.setMaxInactiveInterval(Constants.ADMIN_MAX_TIME_INACTIVE);
	
	}

	public static void logOut(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.removeAttribute("loggedIn"); //Just to be sure
			session.invalidate();
		}
	}
}
