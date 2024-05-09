<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/cart.css" />
</head>
<body>
	<jsp:include page="Header.jsp"/>
    <h1>Your Shopping Cart</h1>
    <table border="1">
        <tr>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="item" items="${cartItems}">
            <tr>
                <td>${item.productName}</td>
                <td>${item.price}</td>
                <td>
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="cartId" value="${item.cartId}">
                        <input type="hidden" name="action" value="update">
                        <input type="number" name="quantity" value="${item.quantity}" min="1">
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>${item.price * item.quantity}</td>
                <td>
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="cartId" value="${item.cartId}">
                        <input type="hidden" name="action" value="delete">
                        <input type="submit" value="Remove">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="checkout">
        <a href="${pageContext.request.contextPath}/CheckoutServlet">Checkout</a> <!-- Link to your checkout process -->
    </div>
</body>
</html>
