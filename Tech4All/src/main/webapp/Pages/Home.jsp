<%@ page import="util.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home| Tech4All</title>
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
<h1>Hello <%=cookieUsername %>.Welcome to this page</h1>
<a href="/Tech4All/Pages/Admin.jsp">Admin Dashboard</a>
<a href="UserProfile.jsp">Profile</a>
<h1 style="text-weight:bold ;text-align:center;"> Welcome</h1>
</body>
</html>