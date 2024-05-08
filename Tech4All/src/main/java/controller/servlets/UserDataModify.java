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

import model.UserModel;
import util.StringUtils;

/**
 * Servlet implementation class UserDataModify
 */

@WebServlet(asyncSupported = true, urlPatterns = "/UserDataModify")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB max size of file to be uploaded
maxRequestSize = 1024 * 1024 * 50) //multiple files could be uploaded
public class UserDataModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final controller.database.DatabaseController dbController;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDataModify() {
    	 this.dbController = new controller.database.DatabaseController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println("This is the id" + username);
		UserModel user = dbController.getUserDetail(username);
		 if (user != null) {
		        System.out.println("User details retrieved: " + user.toString()); // Debug statement
		    } else {
		        System.out.println("User details not found for ID: " + username); // Debug statement
		    }
		request.setAttribute("user", user);
		request.getRequestDispatcher("/Pages/UserProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post triggered");
		String username = request.getParameter("username");
		
		String firstname = request.getParameter(StringUtils.NAME);
		String lastname = request.getParameter(StringUtils.BRAND);
		String dobString = request.getParameter(StringUtils.BIRTHDAY);
		LocalDate dob = LocalDate.parse(dobString);
		String gender = request.getParameter(StringUtils.GENDER);
		String email = request.getParameter(StringUtils.EMAIL);
		String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
		String address = request.getParameter(StringUtils.ADDRESS);
		Part pfpImg = request.getPart("image");
		
		UserModel usermodel = new UserModel(firstname, lastname, dob, gender, email, phoneNumber, address,username, pfpImg);
		int result = dbController.updateUserInfo(usermodel);
		System.out.println(result);
		String savePath = StringUtils.IMAGE_DIR_SAVE_USER;
	    String fileName = usermodel.getPfpUrl();
	    if(!fileName.isEmpty() && fileName != null)
	    	pfpImg.write(savePath + fileName); 
	    
		if (result ==1){
			request.getRequestDispatcher(StringUtils.USER_DATA_SERVLET).forward(request, response);
		}else {
			request.getRequestDispatcher(StringUtils.USER_DATA_SERVLET).forward(request,  response);
		}
		
		
	}

}
