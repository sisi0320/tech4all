package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DatabaseController;
import model.UserModel;
import util.StringUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = "/RegisterServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB max size of file to be uploaded
maxRequestSize = 1024 * 1024 * 50) //multiple files could be uploaded

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter(StringUtils.USER_NAME);
		String firstName = request.getParameter(StringUtils.FIRST_NAME);
		String lastName = request.getParameter(StringUtils.LAST_NAME);
		String dobString = request.getParameter(StringUtils.BIRTHDAY);
		LocalDate dob = LocalDate.parse(dobString);
		String gender = request.getParameter(StringUtils.GENDER);
		String email = request.getParameter(StringUtils.EMAIL);
		String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
		String address = request.getParameter(StringUtils.ADDRESS);
		String password = request.getParameter(StringUtils.PASSWORD);
		String retypePassword = request.getParameter(StringUtils.RETYPE_PASSWORD);
		Part pfpImg = request.getPart(StringUtils.PFP_IMG);
		
	
		String namePattern = "[a-zA-Z]+";
		if (!firstName.matches(namePattern)|| !lastName.matches(namePattern)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Invalid Name pattern");
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		String userNamePattern = "[a-zA-Z0-9]{6,}";
		if (!userName.matches(userNamePattern)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Invalid Username");
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		LocalDate currentDate = LocalDate.now();
		if(dob.isAfter(currentDate)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Invalid date");
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		String numberPattern = "\\+977\\d{10}";
		if(!phoneNumber.matches(numberPattern)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Invalid phone number");
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		String passwordPattern = "[a-zA-Z0-9!@#$%^&*]{6,}";
		if(!password.matches(passwordPattern)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Invalid password");
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		if(!password.equals(retypePassword)) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Password doesn't match");
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		int userResult = dbController.checkUsername(userName);
		if(userResult == 1) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.USERNAME_EXISTS);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		int mailResult = dbController.checkMail(email);
		if(mailResult == 1) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.MAIL_EXISTS);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		int numResult = dbController.checkNumber(phoneNumber);
		if(numResult == 1) {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.PHONE_EXISTS);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			return;
		}
		
		UserModel userModel = new UserModel(firstName, lastName, dob, gender, email, phoneNumber, address, userName, password, pfpImg);
		int result = dbController.addUsers(userModel);
		
		String savePath = StringUtils.IMAGE_DIR_SAVE_USER;
	    String fileName = userModel.getPfpUrl();
	    if(!fileName.isEmpty() && fileName != null)
	    	pfpImg.write(savePath + fileName);
	    
		if (result == 1) {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE);
			request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
		}else if(result ==0) {
			//Redirect to the same register page with form data mistake
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.REGISTER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
		}else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
		}
	}

}
