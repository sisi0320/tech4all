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
	<div class="products_list" id="products_list">
		<h3>Products List</h3>
		<table class="table">
      		<thead><tr>
               <th>Product ID</th>
               <th>Image</th>
               <th>Name</th>
               <th>Brand</th>
               <th>Category</th>
               <th>Description</th>
               <th>Price</th>
               <th>Stock</th>
               <th>Action</th>			
         	</tr></thead>
       		<tbody>
       			<c:if test="${empty productsList}">
					<p>No products found.</p>
				</c:if>
       			<c:forEach var="products" items="${productsList}">
       				<tr>
       					<td>${products.prod_id}</td>
       					<td>
           					<img src="/Tech4All/resources/Images/Products/${products.prodImgUrl}" alt="Product Image">
        				</td>
       					<td>${products.prod_name}</td>
       					<td>${products.brand}</td>
       					<td>${products.category}</td>
       					<td>${products.description}</td>
       					<td>${products.price}</td>
       					<td>${products.stock}</td>
       					<td><div class="action">
       						<form action="${pageContext.request.contextPath}/ModificationServlet" method="post">
  								<input type="hidden" name="editID" value="${products.prod_id}">
  								<button type="submit">
							    <i class="fa-solid fa-pen-to-square"></i>
							  </button>
							</form>     						
   							<form  action="${pageContext.request.contextPath}/ModificationServlet" method="post">
  								<input type="hidden" name="deleteID" value="${products.prod_id}">
  								<button type="submit" onclick="confirmDelete('${products.prod_id}')">
							    <i class="fa-solid fa-trash"></i>
							  </button>
							</form>
       					</div></td>
       				</tr>       					
       			</c:forEach>	           		
       		</tbody>
       </table>	
	</div>
</body>
</html>