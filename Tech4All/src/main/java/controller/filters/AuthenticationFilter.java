package controller.filters;

import java.io.IOException;
import java.util.logging.Logger;

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
		 final Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
	    
		String uri = req.getRequestURI();
		logger.info("Request URI: " + uri);
		
		//Immediately allows requests for CSS files and the home page to pass through without further checks
		if(uri.endsWith(".css") || uri.endsWith(".png")|| uri.endsWith(StringUtils.REGISTER) || uri.endsWith(StringUtils.LOGIN)){
			//delegates control to the next filter in the chain or if this filter is the last one in the chain, to the servlet that services the incoming request
			logger.info("Allowing request for CSS files or home or product or register page: " + uri);
			chain.doFilter(request, response);
			return;
		}
		
		//Check if the requested URI indicates a login page
		boolean isLogin = uri.endsWith(StringUtils.LOGIN);
		//Check if the requested URI indicates a login servlet
		boolean isLoginServlet = uri.endsWith(StringUtils.LOGIN_SERVLET);
		boolean isRegisterPage = uri.endsWith(StringUtils.REGISTER);
		boolean isRegisterServlet = uri.endsWith(StringUtils.REGISTER_SERVLET);
		
			
		//Check if the requesed URI indicates a logout servlet
		boolean isLogoutServlet = uri.endsWith(StringUtils.LOGOUT_SERVLET);
		//Attempt to retrieve the current session associated with the request
		//If false is passed as an argument and no session exists, it returns null
		HttpSession session = req.getSession();
		//Check if a session exists and if the USERNAME attribute is set in the session
		//If both conditions are true, it indicates that the user is logged in
		boolean isLoggedIn = session !=null && session.getAttribute(StringUtils.USER_NAME )!= null;
		boolean isAdmin = session!=null && session.getAttribute(StringUtils.ROLES) != null && 
				session.getAttribute(StringUtils.ROLES).equals("Admin");
		
		
		//if the user is not logged in and doesnot try to access home or access these servlet:
		if (!isLoggedIn && !uri.endsWith(StringUtils.HOME) && !(isLogin || isLoginServlet
				|| isRegisterPage || isRegisterServlet)) {
		        res.sendRedirect(req.getContextPath() + StringUtils.LOGIN_PAGE);
		    }// Redirect logged-in users to the index page if trying to access login page again 
		else if (isLoggedIn && !uri.endsWith(StringUtils.HOME_PAGE) && !(!isLogin || isLogoutServlet)) { 
		        res.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
		    }//Redirect logged in admin to admin page if trying to access home page
		else if(isLoggedIn && isAdmin  && uri.endsWith(StringUtils.HOME_PAGE)) {
	    	res.sendRedirect(req.getContextPath()+ StringUtils.ADMIN_PAGE);
	    }//Redirect logged in users to home page if trying to access admin dashboard
		else if(isLoggedIn && uri.endsWith(StringUtils.ADMIN_PAGE) && !isAdmin){
		    	res.sendRedirect(req.getContextPath() + StringUtils.HOME_PAGE);
		    }else {
				logger.info("Allowing request");		    	
		        // Allow access to the requested resource if user is logged in or accessing unprotected resources
		        chain.doFilter(request, response);
		    }	
	}
	@Override
	public void destroy() {
	}
}
