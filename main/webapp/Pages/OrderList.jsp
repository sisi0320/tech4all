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
		<h3>Orders List</h3>
		<table class="table">
      		<thead><tr>
               <th>Order ID</th>
               <th>User</th>
               <th>Date</th>
               <th>Address</th>
               <th>Total</th>
               <th>Payment Method</th>
               <th>Status</th>	
               <th>Change Status</th>		
         	</tr></thead>
       		<tbody>
       			<c:if test="${empty ordersList}">
					<p>No orders found.</p>
				</c:if>
       			<c:forEach var="order" items="${ordersList}">
       				<tr>
       					<td>${order.orderId}</td>       					
       					<td>${order.username}</td>
       					<td>${order.date}</td>
       					<td>${order.address}</td>
       					<td>${order.total}</td>
       					<td>${order.payment}</td>
       					<td>${order.status}</td>
       					<td><form id="updateStatus" action="${pageContext.request.contextPath}/OrdersList?orderId=${order.orderId}" method="post">
       						<select id="status" name="status" required>
								<option value="Pending"  ${order.status == 'Pending' ? 'selected' : ''}>Pending</option>
								<option value="Processing" ${order.status == 'Processing' ? 'selected' : ''}>Processing</option>
								<option value="Shipped" ${order.status == 'Shipped' ? 'selected' : ''}>Shipped</option>
							</select>
  							<input type="hidden" name="deleteID" value="${order.orderId}">
  							<button type="submit" onclick="confirmUpdate('${order.orderId}', '${order.status}')">
							    Change Status
							 </button>
						</form></td>
       				</tr>       					
       			</c:forEach>	           		
       		</tbody>
       </table>	
	</div>
	<script>
	function confirmUpdate(orderId, status) {
		if (confirm("Are you sure you want to update the status of this order: " + prod_id
				+ "to :" + status +"?")) {
			document.getElementById("deleteForm-" + prod_id).submit();
		}
	}
	</script>
</body>
</html>