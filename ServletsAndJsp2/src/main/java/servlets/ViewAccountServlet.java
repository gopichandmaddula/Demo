package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;
import user.User;

public class ViewAccountServlet extends HttpServlet {
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/viewaccount.jsp").include(req, resp);
		clearSession(req);
	}
	
	private void clearSession(HttpServletRequest req) {
		req.getSession().removeAttribute("accountsList");
		req.getSession().removeAttribute("vat");
		req.getSession().removeAttribute("error");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		clearSession(req);
		
		req.getSession().setAttribute("vat", "single");
		req.getRequestDispatcher("/viewaccount.jsp").include(req, resp);

		String userId = (String) req.getParameter("userId");
		List<User> usersList = new ArrayList<>();
		User user = userService.findOneUser(userId);
		if (user != null) {
			usersList = new ArrayList<>();
			usersList.add(user);
			req.removeAttribute("vat");
			req.getSession().setAttribute("accountsList", usersList);
		}
		resp.sendRedirect(req.getContextPath()+"/home?vat=single");
	}
}
