package model;

import java.time.LocalDate;

public class OrderModel {
	 private int orderId;
	 private String username;
	 private LocalDate date;
	 private String address; // Add this line
	 private String payment;
	 private String status;
	 
	 public OrderModel(int orderId, String username, LocalDate date, String address, String payment, String status){
		this.orderId = orderId;
		this.username = username;
		this.date = date;
		this.address = address;
		this.payment = payment;
		this.status = status;
	 }
	 public int getOrderId() {
		return orderId;
	}
	 public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	 
	 public String getUsername() {
		return username;
	}
	 public void setUsername(String username) {
		this.username = username;
	}
	 
	 public LocalDate getDate() {
		return date;
	}
	 public void setDate(LocalDate date) {
		this.date = date;
	}
	 
	 public String getAddress() {
		return address;
	}
	 public void setAddress(String address) {
		this.address = address;
	}
	 
	 public String getPayment() {
		return payment;
	}
	 public void setPayment(String payment) {
		this.payment = payment;
	}
	 
	 public String getStatus() {
		return status;
	}
	 public void setStatus(String status) {
		this.status = status;
	}
}
