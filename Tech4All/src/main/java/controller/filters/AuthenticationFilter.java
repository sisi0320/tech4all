package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtils;

public class AuthenticationFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
	    
		String uri = req.getRequestURI();
		
		//Immediately allows requests for CSS files and the home page to pass through without further checks
		if(uri.endsWith(".css") || uri.endsWith(StringUtils.HOME_PAGE) || uri.endsWith(StringUtils.PRODUCT_PAGE)) {
			//delegates control to the next filter in the chain or if this filter is the last one in the chain, to the servlet that services the incoming request
			chain.doFilter(request, response);
			return;
		}
		
		//Check if the requested URI indicates a login page
		boolean isLogin = uri.endsWith(StringUtils.LOGIN_PAGE);
			
		//Check if the requested URI indicates a login servlet
		boolean isLoginServlet = uri.endsWith(StringUtils.LOGIN_SERVLET);
			
		//Check if the requesed URI indicates a logout servlet
		boolean isLogoutServlet = uri.endsWith(StringUtils.LOGOUT_SERVLET);
		//Attempt to retrieve the current session associated with the request
		//If false is passed as an argument and no session exists, it returns null
			HttpSession session = req.getSession(false);
			//Check if a session exists and if the USERNAME attribute is set in the session
			//If both conditions are true, it indicates that the user is logged in
			boolean isLoggedIn = session !=null && session.getAttribute(StringUtils.USER_NAME)!= null;
				
			//If user is not logged in and the requested uri does not indicate an attempt to access the login page or login servlet,
			//redirect the user to the login page to authenticate
			if(!isLoggedIn && !(isLogin || isLoginServlet)) {
				res.sendRedirect(req.getContextPath() + StringUtils.LOGIN_PAGE);
			}
			//if the user is logged in and the requested uri does not indicate an attempt to
			//access the login page or logout servlet, redirect the user to the home page to prevent access to login related [ages
			else if(isLoggedIn && !(!isLogin || isLogoutServlet)) {
				res.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
			}
			//if none of the above conditions are met, allow the request to continue down the filter chain
			else {
				chain.doFilter(request, response);
			}
	    }

	@Override
	public void destroy() {
	}
}
