package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
