<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/productupdate.css" />
<script src="https://kit.fontawesome.com/cfef85a76c.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="AdminHeader.jsp"/>
	<div class="update_products">  
		<h3>Update Details</h3>
		<form class="prodForm" action = "/Tech4All/ModificationServlet?productID=${product.prod_id}" method="post" enctype="multipart/form-data">
		<input type="hidden" name="editID" value="${product.prod_id}">
			<div class="image_div">
				<img src="/Tech4All/resources/Images/Products/${product.prodImgUrl}" alt="Product Image">
				<a class="editImg" id="editImg" href="#">Change Product Image</a>
				<input type="file" id="new_image" name="image" accept="image/*" >
			</div>
			<div class=" details">
			<div class="form_row">
				<label for="Prod_id">Product ID:</label> <input type="number" id="Prod_id" name="Prod_id" 
				 value = "${product.prod_id}">
			</div>
			<div class="form_row">
				<label for="Prod_name">Product Name:</label><input type="text" 
				id="Prod_name" name ="Prod_name" value = "${product.prod_name}" >
			</div>
			<div class="form_row">
				<label for="Category">Category:</label><select id="Category" name="Category" >
						<option value="phones"  ${product.category == 'phones' ? 'selected' : ''}>Phones</option>
						<option value="accessories" ${product.category == 'accessories' ? 'selected' : ''}>Accessories</option>
					</select>
			</div>
			<div class="form_row">
				<label for="brand">Brand:</label><input type="text" id="brand" name="brand" value="${product.brand}" >
			</div>	
			<div class="form_row">
				<label for="description">Description:</label><textarea id="description" name="description"
				 rows="4" cols="50">${product.description} </textarea>
			</div>
			<div class="form_row">
				<label for="price">Price:</label><input type="number" id="price" name="price" value="${product.price}" >
			</div>
			<div class="form_row">
				<label for="stock">Stock:</label><input type="number" id="stock" name="stock" value="${product.stock}" >
			</div>
			<center><button type="submit">Update</button></center>  	
			</div>	
						
		</form>
	</div>
	 <script >
	 document.getElementById("editImg").addEventListener("click", function(){
	        var imageInput = document.getElementById("new_image");
	        if (imageInput.style.display === "none" || imageInput.style.display === "") {
	            imageInput.style.display = "block";
	        } else {
	            imageInput.style.display = "none";
	        }
	    });
	
	 </script>
</body>
</html>