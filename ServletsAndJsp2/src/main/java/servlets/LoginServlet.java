package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;
import user.User;

public class LoginServlet extends HttpServlet {
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//user authentication code
		String userId = (String) req.getParameter("userid");
		String password = (String)req.getParameter("password");
		
		if(userId==null || password==null)
			resp.sendRedirect(req.getContextPath()+"/login");
		
		if(userService.validateUser(userId, password)) {
			User user = userService.findOneUser(userId);
			System.out.println(user.getUserId()+","+user.getPassword());
			req.getSession().setAttribute("userid", user.getUserId());
			req.getSession().setAttribute("role", user.getRole());
			resp.sendRedirect(req.getContextPath()+"/home");
		} else {
			resp.sendRedirect(req.getContextPath()+"/login");
		}		
	}
}
