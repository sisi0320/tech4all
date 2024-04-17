<%@page import="util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In To Your Account</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/login.css" />
</head>
<body>
	
		<nav class="navl">
			<ul>
				<li><a href="#"><img src="/Tech4All/Images/T.png"></a></li>
				<li><a href="#">Home</a></li>
				<li><a href="#">About Us</a></li>
			</ul>
		</nav>
		<div class="formBox">
			<div class="logo">
				<img src="/Tech4All/Images/T.png">
			</div>
			<h2>Login</h2>
			<h3>Welcome, to Tech4All!</h3>
			<div class="row">
				<div class="col">
					<input type="text" id="username" name="username" placeholder="Username" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<input type="password" id="password" name="password" placeholder="Password" required>
				</div>
			</div>
			<button type="submit" class="login-button">Login</button>
			<h5>Don't have an account?</h5><a href="#">Sign In</a>
			<%--Error message if redirected from register --%>
			<% 
			String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
			if(errorMessage!= null && !errorMessage.isEmpty()){
			%>
			<p class="error-message"><%=errorMessage %></p>
			<%
			}
			%>
			<%--Success message if redirected from register --%>
			<% 
			String successMessage = (String) request.getAttribute(StringUtils.SUCCESS_MESSAGE);
			if(successMessage!= null && !successMessage.isEmpty()){
			%>
			<p class="success-message"><%=successMessage %></p>
			<%
			}
			%>
		</div>
		<div class="image">
			<img src="/Tech4All/Images/LoginImg.png">
		</div>
	
		
</body>
</html>