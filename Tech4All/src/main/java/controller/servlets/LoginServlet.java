package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DatabaseController dbController = new DatabaseController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		int loginResult = dbController.getUsersLoginInfo(userName, password);
		
		if(loginResult ==1) {
			//successful login
			HttpSession userSession = request.getSession();
			userSession.setAttribute("username", userName);
			userSession.setMaxInactiveInterval(120);
			
			Cookie userCookie= new Cookie("user", userName);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
			String roles= dbController.getUserRoles(userName);
			if (roles.equals("Admin")){
				request.getRequestDispatcher(StringUtils.ADMIN_PAGE).forward(request, response);
			}
			else {
				request.getRequestDispatcher(StringUtils.HOME_PAGE).forward(request, response);
			}
		}else if(loginResult == 0) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PASSWORD_INCORRECT);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}else if(loginResult==2){
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.NO_ACCOUNT);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
