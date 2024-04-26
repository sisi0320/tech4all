<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="util.StringUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/adminheader.css" />
</head>
<body>
<%
	String userSession = (String) session.getAttribute(StringUtils.USER_NAME);
	String cookieUsername = null;
	String cookieSessionID= null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie cookie: cookies){
			if(cookie.getName().equals(StringUtils.USER)) cookieUsername = cookie.getValue();
			if(cookie.getName().equals(StringUtils.JSESSIONID)) cookieSessionID = cookie.getValue();
		}
	}
%>
	<header>
		<div class="left_head">
			<i class="fa-solid fa-user"></i>
			<div class="dropdown">
				<a href="#">Profile</a>
				<a href="http://localhost:8081//Tech4All/LogOutServlet">Log out</a>
			</div>
			<h3><%=userSession %></h3>
		</div>
		<img src="/Tech4All/Images/T.png"/>
	</header>
	<nav>
		<a href="Pages/Admin.jsp"><i class="fa-solid fa-bars"></i>Overview</a>
		<a href="http://localhost:8081//Tech4All/ProductsList"><i class="fa-solid fa-box-open"></i>Products</a>
		<a href="#"><i class="fa-solid fa-clipboard-list"></i>Orders</a>
		<a href="#"><i class="fa-solid fa-users"></i>Customers</a>
	</nav>
</body>
</html>