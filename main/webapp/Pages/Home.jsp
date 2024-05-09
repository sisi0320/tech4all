<%@ page import="util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="model.ProductModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home| Tech4All</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/home.css" />
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	
</head>
<body>
	<jsp:include page="Header.jsp"/>
	<div class="maindiv">
		<div class="img-slider">
			<img id='slide-1' src="/Tech4All/Images/phone2.png">
			<img id='slide-3' src="/Tech4All/Images/phone3.jpg">
			<img id='slide-2' src="/Tech4All/Images/phone1.png">
		</div>


		<div class="pq">
			<div class="pq-img">
				<img src="/Tech4All/Images/phone1.png">
			</div>
			<div class="pq-q">
				<h1 style="margin-top: 75px;">Shop the</h1>
				<h1>Latest</h1>
				<h1 style="color: #FF9419;">Brands</h1>
				<h1>From</h1>
				<img src="/Tech4All/Images/T.png">
			</div>
		</div>
		
		<div class="b-sellers">
			<h1>Our Products</h1>
			<c:if test="${empty previewProducts}">
    <p>No products found.</p>
</c:if>
	<div class="product-container">
		<div class="product-slider">
        <!-- Repeat this div for each product in the slider -->
        <c:forEach items="${previewProducts}" var="product">
        <div class="product-card" data-category="${product.category}" >
           <img src="/Tech4All/resources/Images/Products/${product.prodImgUrl}" alt="${product.prod_name}">
           <h3>${product.prod_name} - ${product.brand}</h3>
           <p>Price: $${product.price}</p>
           <button type="button" onclick="addToCart(${product.prod_id})">Add to Cart</button>
           <button type="button" onclick="openDetailsModal('modal${product.prod_id}')">View Details</button>
        </div>
           <div id="modal${product.prod_id}" class="modal">
              <div class="modal-content">
                  <span class="close-btn" onclick="closeDetailsModal('modal${product.prod_id}')">&times;</span>
                  <h3>${product.prod_name} - ${product.brand}</h3>
                  <p>Price: $${product.price}</p>
                  <p>Description: ${product.description}</p>
                  <p>Stock: ${product.stock}</p>
                  <p>Category: ${product.category}</p>
              </div>
         </div>
        </c:forEach> 
       
    </div>
		

	</div>
</body>
<script type="text/javascript">
//Function to open the details modal
function openDetailsModal(modalId) {
    var modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = 'block';
    } else {
        console.error('Modal not found:', modalId);
    }
}

// Function to close the details modal
function closeDetailsModal(modalId) {
    var modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = 'none';
    } else {
        console.error('Modal not found:', modalId);
    }
</script>
</html>