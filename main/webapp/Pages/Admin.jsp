<%@ page import="util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/admin.css" />
<script src="https://kit.fontawesome.com/cfef85a76c.js" crossorigin="anonymous"></script>
</head>
<body> 
	<jsp:include page="AdminHeader.jsp"/>
	 <main>
		<div class="products_view">
			<h3>Inventory</h3>
			<div class="products">
			
			<div class="prod_ops">
				<a href="#" id = "openAdd">Add new product</a><br>
				<a href="http://localhost:8081//Tech4All/ProductsList">View all</a>
			</div>
			</div>
		</div>
		<div class="sidebyside">
			<div class="orders_view">
				<h3>Order List</h3>
                <div class="order_item">
                    <img src="/path/to/order_thumbnail.jpg" alt="Order Thumbnail">
                    <button onclick="openOrderDetails(101)">View Details</button>
                </div>
			</div>	
			<div class="users_view">
				<h3>Customer List</h3>
                <div class="user_item">
                    <img src="/path/to/user_avatar.jpg" alt="User Avatar">
                    <button onclick="openUserDetails(1)">View Details</button>
                </div>
			</div>
		</div>
		<!-- form to add a new product which will be visible only after the link "Add new product is selected"--> 
		<div class="addWindow" id="addWindow">
  			<div class="add-products">
    			<span class="close" id="closeAdd">&times;</span>
    			<h3>Add a New Product</h3>
    			<form class="prodForm" action = "/Tech4All/AddProducts" method="post" enctype="multipart/form-data">
    				<div class="form_row">
						<label for="Prod_id">Product ID:</label> <input type="number" 
						id="Prod_id" name="Prod_id" required>
					</div>
					<div class="form_row">
						<label for="Prod_name">Product Name:</label><input type="text" 
						id="Prod_name" name ="Prod_name" required>
					</div>
					<div class="form_row">
						<label for="Category">Category:</label><select id="Category" name="Category" required>
								<option value="phones">Phones</option>
								<option value="accessories">Accessories</option>
							</select>
					</div>
					<div class="form_row">
						<label for="brand">Brand:</label><input type="text" id="brand" name="brand" required>
					</div>	
					<div class="form_row">
						<label for="description">Description:</label><textarea id="description" name="description"
						 rows="4" cols="50"></textarea>
					</div>
					<div class="form_row">
						<label for="price">Price:</label><input type="number" id="price" name="price" required>
					</div>
					<div class="form_row">
						<label for="stock">Stock:</label><input type="number" id="stock" name="stock" required>
    				</div>
    				<div class="form_row">
						<label for="image">Product Image:</label> <input type="file"
						id="image" name="image" accept="image/*" required>
    				</div>   
    				<center><button type="submit">Add</button></center>					
    			</form>
  			</div>
		</div>
	</main> 
	
	 <script src="/Tech4All/JavaScript/admin.js"></script>
</body>
</html>