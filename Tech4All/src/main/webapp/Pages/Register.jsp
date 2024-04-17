<%@page import="util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Your Account</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/register.css" />
</head>
<body>
<div class="container">
	<div class="top_bar">
		<img src ="/Tech4All/Images/T.png" class="logo"/>
		<a href="Pages/Home.jsp">Home</a>
		<a href="Pages/AboutUs.jsp">About Us</a>
	</div>
	<div class="image">
		<img src="/Tech4All/Images/RegisterPageImg.png" class="side_img"/>
	</div>
	<div class="formbox">
		<%-- Display error message if it exists --%>
		<%
		String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
		if(errorMessage != null && !errorMessage.isEmpty()){
		%>
		<p class="error-message"><%=errorMessage%></p>
		<%
		}
		%>
		<h1>Sign Up</h1>
		<form action="/Tech4All/RegisterServlet" method="post">
			<div class="row">
				<div class="col">
					<label for="firstName">First Name:</label> <input type="text"
						id="firstName" name="firstName" required>
				</div>
				<div class="col">
					<label for="lastName">Last Name:</label> <input type="text"
						id="lastName" name="lastName" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="username">UserName:</label> <input type="text"
						id="username" name="username" required>
				</div>
				<div class="col">
					<label for="birthday">Birthday:</label> <input type="date"
						id="birthday" name="birthday" required>
				</div>

			</div>
			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender" required>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="col">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="phoneNumber">Phone Number:</label> <input type="tel"
						id="phoneNumber" name="phoneNumber" required>
				</div>
				<div class="col">
					<label for="subject">Address:</label> <input type="addr" id="address"
						name="address" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
				<div class="col">
					<label for="retypePassword">Retype Password:</label> <input
						type="password" id="retypePassword" name="retypePassword" required>
				</div>
			</div>
			<center><button type="submit">Register</button></center>
			<center><h5> Already have an account?</h5><a href="Pages/Login.jsp">Log In</a></center>
		</form>
		
	</div>
</div>
</body>
</html>