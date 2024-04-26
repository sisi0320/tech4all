package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PasswordEncryptionWithAes;
import model.ProductModel;
import model.UserModel;
import util.StringUtils;

public class DatabaseController {
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/tech4all";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
		
	}
	//To add new users
	public int addUsers(UserModel userModel) {
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_USERS);
			
			st.setString(1, userModel.getFirstName());
			st.setString(2,userModel.getLastName());
			st.setString(3, userModel.getUsername());
			st.setDate(4, Date.valueOf(userModel.getDob()));
			st.setString(5, userModel.getGender());
			st.setString(6, userModel.getPhoneNumber());
			st.setString(7, userModel.getEmail());
			st.setString(8, userModel.getAddress());
			st.setString(9, PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword()));
			
			int result = st.executeUpdate();
			return result > 0 ? 1: 0;
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
		}
	//Getting user info for login process
	public int getUsersLoginInfo(String username, String password) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USERS_INFO_LOGIN);
			st.setString(1,username);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				//username and password match in the database
				String userDb = rs.getString("username");
				String passwordDb = rs.getString("password");
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);
				
				if (decryptedPwd != null && userDb.equals(username) && decryptedPwd.equals(password)) {
					return 1;
				}else {
					return 0;
				}
			}else {
				//no matching record found
				return 2;
			}	
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	//Method to get a user's role
	public String getUserRoles(String username) {
		String userRole = null;
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USERS_ROLE);
			st.setString(1,username);
			
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				userRole = rs.getString("Roles");
				
			}
	}catch(SQLException | ClassNotFoundException e) {
		 e.printStackTrace();
	}
		return userRole;
	}
	
	//to check if username already exists
	public int checkUsername(String username) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.CHECK_USERNAME);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else {
				return 0;
			}
		}catch(SQLException| ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	//to check if mail id already exists
	public int checkMail(String mail) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.CHECK_MAIL);
			st.setString(1, mail);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else {
				return 0;
			}
		}catch(SQLException| ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	//to check if phone number already exists
	public int checkNumber(String number) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.CHECK_NUMBER);
			st.setString(1, number);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else {
				return 0;
			}
		}catch(SQLException| ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	//list of all users
	public ArrayList<UserModel> getAllUsersInfo(){
		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement("SELECT * FROM users");
			ResultSet result = stmt.executeQuery();
			
			ArrayList<UserModel> users = new ArrayList<UserModel>();
			
			while(result.next()) {
				UserModel user = new UserModel();
				user.setFirstName(result.getString("First_Name"));
				user.setLastName(result.getString("Last_Name"));
				user.setDob(result.getDate("DOB").toLocalDate());
				user.setEmail(result.getString("Mail"));
				user.setGender(result.getString("Gender"));
				user.setPhoneNumber(result.getString("Phone"));
				user.setAddress(result.getString("Address"));
				user.setUsername(result.getString("Username"));
				
				System.out.println(user.getFirstName());
				users.add(user);
			}
			return users;
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//To add new products to the database
	public int addProducts(ProductModel productModel) {
		
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_PRODUCTS);
			
			st.setInt(1, productModel.getProd_id());
			st.setString(2,productModel.getProd_name());
			st.setString(3, productModel.getBrand());
			st.setFloat(4, productModel.getPrice());
			st.setString(5, productModel.getDescription());
			st.setInt(6, productModel.getStock());
			st.setString(7, productModel.getCategory());
			st.setString(8, productModel.getProdImgUrl());
			
			int result = st.executeUpdate();
			return result > 0 ? 1: 0;
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
		}
	
	//list for all products
	public ArrayList<ProductModel> getAllProductDetails(){
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement("SELECT * FROM products");
			
			
			ResultSet result = st.executeQuery();
			
			ArrayList<ProductModel> products = new ArrayList<ProductModel>();
			
			while(result.next()) {
				ProductModel product = new ProductModel();
				product.setProd_id(result.getInt("ProductID"));
				product.setProd_name(result.getString("Name"));
				product.setBrand(result.getString("Brand"));
				product.setPrice(result.getFloat("Price"));
				product.setDescription(result.getString("Description"));
				product.setStock(result.getInt("Stock"));
				product.setCategory(result.getString("Category"));
				product.setProdUrlFromDB(result.getString("P_Image"));
				
				System.out.println(product.getProd_id());
				System.out.println(product.getProd_name());
				System.out.println(product.getBrand());
				System.out.println(product.getPrice());
				System.out.println(product.getDescription());
				System.out.println(product.getStock());
				System.out.println(product.getCategory());
				System.out.println(product.getProdImgUrl());
				products.add(product);
			}
			return products;
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//method that deletes a product 
	public int deleteProduct(String prod_id) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_PRODUCT);
			st.setString(1, prod_id);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	//method to update a prodct's details
	/*
	 * public int updateProduct(ProductModel productmodel ) { try( Connection con =
	 * getConnection()){ PreparedStatement st =
	 * con.prepareStatement(StringUtils.UPDATE_PRODUCT); st.setString(1,
	 * productmodel.getProd_name()); st.setString(2, productmodel.getBrand());
	 * st.setFloat(3, productmodel.getPrice()); st.setString(4,
	 * productmodel.getDescription()); st.setInt(5, productmodel.getStock());
	 * st.setString(6, productmodel.getCategory()); st.setString(7,
	 * productmodel.getProdImgUrl()); st.setInt(8, productmodel.getProd_id());
	 * return st.executeUpdate(); }catch (SQLException | ClassNotFoundException ex)
	 * { ex.printStackTrace(); return -1; } }
	 */}
