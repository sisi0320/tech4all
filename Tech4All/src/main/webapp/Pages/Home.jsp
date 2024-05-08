<%@ page import="util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<img id='slide-1' src="images/phone2.webp">
			<img id='slide-3' src="images/phone3.jpg">
			<img id='slide-2' src="images/phone1.webp">
		</div>


		<div class="pq">
			<div class="pq-img">
				<img src="images/phone1.webp">
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
			<h1>Best sellers</h1>
						<div class="product-slider">
        <!-- Repeat this div for each product in the slider -->
        <div class="product-card">
            <img src="https://via.placeholder.com/150" alt="Best Seller Product 1">
            <h3 data-product-name="Best Seller Product Name 1">Best Seller Product Name 1</h3>
            <p data-product-price="$29.99">$29.99</p>
            <button>Add to Cart</button>
            <a href="productdetail.html?productId=1" class="detail-link">View Details</a>
            <div class="rating">★★★★☆</div>
        </div>
        <div class="product-card">
            <img src="https://via.placeholder.com/150" alt="Best Seller Product 2">
            <h3 data-product-name="Best Seller Product Name 2">Best Seller Product Name 2</h3>
            <p data-product-price="$29.99">$29.99</p>
            <button>Add to Cart</button>
            <a href="productdetail.html?productId=2" class="detail-link">View Details</a>
            <div class="rating">★★★★☆</div>
        </div>
        <div class="product-card">
            <img src="https://via.placeholder.com/150" alt="Best Seller Product 3">
            <h3 data-product-name="Best Seller Product Name 3">Best Seller Product Name 3</h3>
            <p data-product-price="$29.99">$29.99</p>
            <button>Add to Cart</button>
            <a href="productdetail.html?productId=3" class="detail-link">View Details</a>
            <div class="rating">★★★★☆</div>
        </div>
        <div class="product-card">
            <img src="https://via.placeholder.com/150" alt="Best Seller Product 4">
            <h3 data-product-name="Best Seller Product Name 4">Best Seller Product Name 4</h3>
            <p data-product-price="$29.99">$29.99</p>
            <button>Add to Cart</button>
            <a href="productdetail.html?productId=4" class="detail-link">View Details</a>
            <div class="rating">★★★★☆</div>
        </div>
        <div class="product-card">
            <img src="https://via.placeholder.com/150" alt="Best Seller Product 5">
            <h3 data-product-name="Best Seller Product Name 5">Best Seller Product Name 5</h3>
            <p data-product-price="$29.99">$29.99</p>
            <button>Add to Cart</button>
            <a href="productdetail.html?productId=5" class="detail-link">View Details</a>
            <div class="rating">★★★★☆</div>
        </div>
    </div>
		

	</div>
</body>
<script type="text/javascript">

</script>
</html>