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
       			<c:forEach var="product" items="${productsList}">
       				<tr>
       					<td>${product.prod_id}</td>
       					<td>
           					<img src="/Tech4All/resources/Images/Products/${product.prodImgUrl}" alt="Product Image">
        				</td>
       					<td>${product.prod_name}</td>
       					<td>${product.brand}</td>
       					<td>${product.category}</td>
       					<td>${product.description}</td>
       					<td>${product.price}</td>
       					<td>${product.stock}</td>
       					<td><div class="action">
       						   
							
							<a href="${pageContext.request.contextPath}/ModificationServlet?productID=${product.prod_id}">
							<i class="fa-solid fa-pen-to-square"></i>
							</a>						
   							<form id="deleteForm-${product.prod_id}" action="${pageContext.request.contextPath}/ModificationServlet" method="post">
  								<input type="hidden" name="deleteID" value="${product.prod_id}">
  								<button type="submit" onclick="confirmDelete('${product.prod_id}')">
							    <i class="fa-solid fa-trash"></i>
							  </button>
							</form>
       					</div></td>
       				</tr>       					
       			</c:forEach>	           		
       		</tbody>
       </table>	
	</div>
	<script>
	function confirmDelete(prod_id) {
		if (confirm("Are you sure you want to delete this product: " + prod_id
				+ "?")) {
			document.getElementById("deleteForm-" + prod_id).submit();
		}
	}
	</script>
</body>
</html>