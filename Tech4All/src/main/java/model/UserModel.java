package model;

import java.time.LocalDate;

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
	
	public UserModel(String firstName, String lastName, LocalDate dob, String gender, String email, String phoneNumber, String address, String username, String password ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public String getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
}
