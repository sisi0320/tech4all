<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.StringUtils" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/data_tables.css" />
<script src="https://kit.fontawesome.com/cfef85a76c.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="AdminHeader.jsp"/>
	<div class="list" id="products_list">
		<h3>Users List</h3>
		<table class="table">
      		<thead><tr>
               <th>Username</th>
               <th>Full Name</th>
               <th>DOB</th>
               <th>Gender</th>
               <th>Phone Number</th>
               <th>Email ID</th>
               <th>Address</th>		
         	</tr></thead>
       		<tbody>
       			<c:if test="${empty usersList}">
					<p>No products found.</p>
				</c:if>
       			<c:forEach var="users" items="${usersList}">
       				<tr>
       					<td>${users.username}</td>
       					<td>${users.firstName} ${users.lastName}</td>
       					<td>${users.dob}</td>
       					<td>${users.gender}</td>
       					<td>${users.phoneNumber}</td>
       					<td>${users.email}</td>
       					<td>${users.address}</td>
       				</tr>       					
       			</c:forEach>	           		
       		</tbody>
       </table>	
	</div>
</body>
</html>