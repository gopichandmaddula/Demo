package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;
import user.User;

public class AddAccountServlet extends HttpServlet {
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/addaccount.jsp").include(req, resp);
		clearSession(req);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		clearSession(req);
		
		String userId = (String)req.getParameter("userId");
		String name = (String)req.getParameter("name");
		String password = (String)req.getParameter("password");
		String role = (String)req.getParameter("role");
		
		if(userId==null || name==null || password==null || role==null) {
			req.getSession().setAttribute("message", "Invalid inputs.");
		} else {
			User user = new User(userId, name, password, role);
			boolean success = userService.insertUser(user);
			if(success) {
				req.getSession().setAttribute("message", userId + " successfully added.");
			} else {
				req.getSession().setAttribute("message", userId + " couldn't be added");
			}
		}
		resp.sendRedirect(req.getContextPath()+"/home?vat=addaccount");
	}
	
	private void clearSession(HttpServletRequest req) {
		req.getSession().removeAttribute("message");
	}
}
