package util;

import java.io.File;

public class StringUtils {
	//QUERIES
	//For users
	public static final String INSERT_USERS = "INSERT INTO users "
			+ "(First_Name, Last_Name, Username, DOB, Gender, Phone, Mail, Address, Password) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_USERS_INFO_LOGIN = "SELECT * FROM users WHERE Username = ?";
	public static final String GET_USERS_ROLE = "Select Roles FROM users Where Username = ?";
	
	//For products
	//Insert new products into db
	public static final String INSERT_PRODUCTS ="INSERT INTO products "
			+ "(ProductID, Name, Brand, Price, Description, Stock, Category,P_Image) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_PRODUCT= "DELETE FROM products WHERE ProductID = ?";
	public static final String UPDATE_PRODUCT = "UPDATE products SET Name=?, Brand=?, Price=?, Description=?, Stock=?, Category=?,P_Image=? WHERE ProductID =?";
	
	//Queries for checking existing values
	public static final String CHECK_USERNAME = "SELECT Username FROM users WHERE Username = ?";
	public static final String CHECK_MAIL = "SELECT Mail FROM users WHERE Mail = ?";
	public static final String CHECK_NUMBER = "SELECT Phone FROM users WHERE Phone = ?";
	
	//normal text for cookie sessions
	public static final String USER = "user";
	public static final String JSESSIONID = "JSESSIONID";
	//normal text for the delete and update function
	public static final String DELETE_ID= "deleteID";
	public static final String EDIT_ID= "editID";
	
	//strings to get parameter from the register form
	public static final String USER_NAME = "username";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "birthday";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBER ="phoneNumber";
	public static final String ADDRESS = "address";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	public static final String ROLES = "roles";
	
	public static final String IMAGE_DIR_PROD = "Apps\\Eclipse\\Tech4All\\src\\main\\webapp\\resources\\Images\\Products\\";
	public static final String IMAGE_DIR_SAVE_PROD = "E:" + File.separator + IMAGE_DIR_PROD;
	
	//strings to get parameters from add produts form
	public static final String PROD_ID = "Prod_id";
	public static final String NAME ="Prod_name";
	public static final String BRAND ="brand";
	public static final String PRICE ="price";
	public static final String DESC = "description";
	public static final String STOCK = "stock";
	public static final String CATEGORY = "Category";
	
	
	//Variables for various messages
	public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered!";
	public static final String REGISTER_ERROR_MESSAGE = "Please correct the form data.";
	
	//messages for adding products
	public static final String ADD_PRODUCT_SUCCESS = "Product successfully added";
	public static final String ADD_PRODUCTS_ERROR = "";
	
	public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred.";
	
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String ERROR_MESSAGE = "errorMessage";
	
	public static final String USERNAME_EXISTS="Account with the username already exists.";
	public static final String MAIL_EXISTS="Account with the email ID already exists.";
	public static final String PHONE_EXISTS="Account with the phone number already exists.";
	public static final String PASSWORD_INCORRECT="Incorrect username or password";
	public static final String NO_ACCOUNT="No account with the given username.";
	
	//Servlet routes
	public static final String LOGIN_SERVLET="/LoginServlet";
	public static final String LOGOUT_SERVLET="/LogOutServlet";
	public static final String REGISTER_SERVLET = "/RegisterServlet";
	public static final String HOME_SERVLET="/home";
	public static final String ADD_PRODUCTS = "/AddProducts";
	
	//JSP routes
	public static final String LOGIN_PAGE = "/Pages/Login.jsp";
	public static final String REGISTER_PAGE = "/Pages/Register.jsp";
	public static final String HOME_PAGE = "/Pages/Home.jsp";
	public static final String PRODUCT_PAGE = "/Pages/Product.jsp";
	public static final String ADMIN_PAGE = "/Pages/Admin.jsp";
	public static final String PRODUCTLIST_PAGE = "/Pages/ProductList.jsp";
	
	//authentication
	public static final String HOME = "/Home.jsp";
	public static final String PRODUCT = "/Product.jsp";
	public static final String REGISTER = "/Register.jsp";
	public static final String LOGIN = "/Login.jsp";
}
