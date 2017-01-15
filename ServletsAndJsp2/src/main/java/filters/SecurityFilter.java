package filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter {
	
	List<String> publicUrls = new ArrayList<>();

	public void init(FilterConfig filterConfig) throws ServletException {
		for(String url : filterConfig.getInitParameter("publicUrls").split(",")) {
			publicUrls.add(url.trim());
			System.out.println(url);
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isPublicUrl = isPublicUrl((HttpServletRequest) request); 
		if (!isPublicUrl) {
			HttpServletRequest req = (HttpServletRequest)request;
			if (req.getSession().getAttribute("role") == null || req.getSession().getAttribute("userid") == null) {
				((HttpServletResponse) response).sendRedirect(req.getContextPath()+"/login");
			} else {
				chain.doFilter(request, response);
			}			
		} else {
			chain.doFilter(request, response);
		}		
	}

	private boolean isPublicUrl(HttpServletRequest req) {
		String contextPath = req.getContextPath();
		String requestURI = req.getRequestURI();
		System.out.println("requestURI" + requestURI);
		System.out.println("contextPath" + contextPath);
		System.out.println("RequestURL" + req.getRequestURL());
		System.out.println("PathInfo" + req.getPathInfo());

		return isPublicUrl(requestURI, contextPath);
	}

	private boolean isPublicUrl(String requestURI, String contextPath) {
		for(String url : publicUrls) {
			if (requestURI.endsWith(url)) {
				return true;
			}
		}

		return false;
	}

	public void destroy() {

	}

}
