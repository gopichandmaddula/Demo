package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		clearSession(req);
		resp.sendRedirect(req.getContextPath()+"/login");
	}
	
	private void clearSession(HttpServletRequest req) {
		Enumeration<String> names = req.getSession().getAttributeNames();
		while(names.hasMoreElements()) {
			String name = (String) names.nextElement();
			req.getSession().removeAttribute(name);
		}
	}
}
