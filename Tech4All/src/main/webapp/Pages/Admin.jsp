<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/admin.css" />
<script src="https://kit.fontawesome.com/cfef85a76c.js" crossorigin="anonymous"></script>
</head>
<body>
	<header>
		<div class="left_head">
			<i class="fa-solid fa-user"></i>
			<div class="dropdown">
				<a href="#">Profile</a>
				<a href="#">Log out</a>
			</div>
			<h3>Hello, <br>User Name</h3>
		</div>
		<img src="/Tech4All/Images/T.png"/>
	</header>
	<nav>
		<a href="Pages/Admin.jsp" class="active"><i class="fa-solid fa-bars"></i>Overview</a>
		<a href="#"><i class="fa-solid fa-box-open"></i>Products</a>
		<a href="#"><i class="fa-solid fa-clipboard-list"></i>Orders</a>
		<a href="#"><i class="fa-solid fa-users"></i>Customers</a>
	</nav>
	<main>
		<div class="products_view">
		
		</div>
		<div class="sidebyside">
			<div class="orders_view">
			
			</div>	
			<div class="users_view">
			
			</div>
		</div>
	</main>
</body>
</html>