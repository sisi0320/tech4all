<%@page import="util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In To Your Account</title>
</head>
<body>
	<div class="login-box">
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
		
		<h2>Login</h2>
		<form action="/Tech4All/LoginServlet" >
			<div class="row">
				<div class="col">
					<label for="username">UserName:</label> 
					<input type="text" id="username" name="username" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> 
					<input type="password" id="password" name="password" required>
				</div>
			</div>
			<button type="submit" class="login-button">Login</button>
		</form>
	</div>
</body>
</html>