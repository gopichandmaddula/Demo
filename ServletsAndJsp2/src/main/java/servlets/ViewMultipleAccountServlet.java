package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.UserServiceImpl;
import user.User;

public class ViewMultipleAccountServlet extends HttpServlet {
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> usersList = userService.findAllUsers();
		
		req.setAttribute("accountsList", usersList);
		req.setAttribute("vat", "multiple");
		req.getRequestDispatcher("/viewallaccounts.jsp").include(req, resp);
	}
}
