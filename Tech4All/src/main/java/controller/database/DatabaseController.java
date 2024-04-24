package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PasswordEncryptionWithAes;
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
	public ArrayList<UserModel> getAllUsersInfo(){
		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement("SELECT * FROM users");
			ResultSet result = stmt.executeQuery();
			
			ArrayList<UserModel> students = new ArrayList<UserModel>();
			
			while(result.next()) {
				UserModel student = new UserModel();
				student.setFirstName(result.getString("First_Name"));
				student.setLastName(result.getString("Last_name"));
				student.setDob(result.getDate("DOB").toLocalDate());
				student.setEmail(result.getString("Mail_ID"));
				student.setGender(result.getString("Gender"));
				student.setPhoneNumber(result.getString("Phone"));
				student.setAddress(result.getString("address"));
				student.setUsername(result.getString("username"));
				
				System.out.println(student.getFirstName());
				students.add(student);
			}
			return students;
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
