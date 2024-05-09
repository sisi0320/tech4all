package model;

import java.time.LocalDate;

public class OrderModel {
	 private int orderId;
	 private String username;
	 private LocalDate date;
	 private String address; // Add this line
	 private String payment;
	 private float total;
	 private String status;
	 
	 public OrderModel(int orderId, String username, LocalDate date, String address, String payment,float total,  String status){
		this.orderId = orderId;
		this.username = username;
		this.date = date;
		this.address = address;
		this.payment = payment;
		this.status = status;
		this.total = total;
	 }
	 public OrderModel(){
		 
	 }
	 public OrderModel(int orderId, LocalDate date,String address, String payment,float total) {
		 this.orderId = orderId;
			this.date = date;
			this.address = address;
			this.payment = payment;
			this.total = total;
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
	 
	 public float getTotal() {
		return total;
	}
	 public void setTotal(float total) {
		this.total = total;
	}
	 
	 public String getStatus() {
		return status;
	}
	 public void setStatus(String status) {
		this.status = status;
	}
}
