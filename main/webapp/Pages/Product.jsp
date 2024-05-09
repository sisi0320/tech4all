<%@ page import="java.util.List" %>
<%@ page import="model.ProductModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Our Products</title>
	<link rel="stylesheet" type="text/css" href="/Tech4All/Stylesheet/product.css" />
	 <script src="/Tech4All/JavaScript/product.js"></script>
	 
</head>
<body>
<jsp:include page="Header.jsp"/>
    <h1>Our Products</h1>
    <div class="view-cart-button-container">
        <a href="${pageContext.request.contextPath}/CartServlet?action=view" class="view-cart-button">View Cart</a>
    </div>

    <!-- Category filter dropdown -->
    <label for="categoryFilter">Choose a category:</label>
    <select id="categoryFilter" onchange="filterCategory()">
        <option value="all">All</option>
        <option value="phones">Phones</option>
        <option value="accessories">Accessories</option>
    </select>
    <div class="product-container">
        <c:forEach items="${products}" var="product">
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
    </div>
</body>
</html>

