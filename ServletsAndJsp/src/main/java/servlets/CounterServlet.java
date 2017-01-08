package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CounterServlet extends HttpServlet {
	
	private Integer counter;
	
	@Override
	public void init() throws ServletException {
		super.init();
		counter = 0;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		counter++;
		resp.getWriter()
			.append("<html><body>")
			.append("You are "+counter+" user visited the page.")
			.append("</body></html>");
	}

}
