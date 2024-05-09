package model;

import java.io.File;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtils;

public class UserModel {
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String gender;
	private String email;
	private String phoneNumber;
	private String address;
	private String username;
	private String password;
	private String pfpUrl;
	
	public UserModel(String firstName, String lastName, LocalDate dob, String gender, String email, String phoneNumber, String address, String username, String password, Part pfpUrl ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.password = password;
		this.pfpUrl = getImageUrl(pfpUrl);
	}
	
	public UserModel(String firstName, String lastName, LocalDate dob, String gender, String email, String phoneNumber, String address, String username, Part pfpUrl ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.pfpUrl = getImageUrl(pfpUrl);
	}
	public UserModel() {
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPfpUrl() {
		return pfpUrl;
	}
	public void setPfpUrl(String pfpUrl) {
		this.pfpUrl = pfpUrl;
	}
	private String getImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_USER;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.jpg";
		}
		return imageUrlFromPart;
	}
	
	public void setPfpFromDB(String imageUrl) {
		this.pfpUrl = imageUrl;
	}
}
