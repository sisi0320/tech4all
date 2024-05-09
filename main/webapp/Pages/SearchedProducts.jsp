<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.ProductModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/product.css" />
	 <script src="/Tech4All/JavaScript/product.js"></script>
</head>
<body>
<jsp:include page="Header.jsp"/>
<c:if test="${empty productsList}">
    <p>No products found.</p>
</c:if>
	<div class="product-container">
        <c:forEach items="${productsList}" var="product">
            <div class="product-item" data-category="${product.category}">
                <img src="/Tech4All/resources/Images/Products/${product.prodImgUrl}" alt="${product.prod_name}">
                <h2>${product.prod_name} - ${product.brand}</h2>
                <p>Price: $${product.price}</p>
                <button type="button" onclick="addToCart(${product.prod_id})">Add to Cart</button>
                <button type="button" onclick="openDetailsModal('modal${product.prod_id}')">View Details</button>
            </div>
            <!-- Hidden modal for product details -->
            <div id="modal${product.prod_id}" class="modal">
                <div class="modal-content">
                    <span class="close-btn" onclick="closeDetailsModal('modal${product.prod_id}')">&times;</span>
                    <h3>${product.prod_name} - ${product.brand}</h3>
                    <p>Price: $${product.price}</p>
                    <p>Description: ${product.description}</p>
                    <p>Stock: ${product.stock}</p>
                    <p>Category: ${product.category}</p>
                </div>
            </div>
        </c:forEach>
</body>
</html>