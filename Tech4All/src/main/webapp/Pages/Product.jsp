<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-commerce Store</title>
    <link rel="stylesheet" href="/Tech4All/Stylesheet/product.css">
    <!-- Slick Slider CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
</head>
<body>
    <header>
        <h1>Our Products</h1>
    </header>
    <nav>
        <ul>
            <li><a href="#best-sellers">Best Sellers</a></li>
            <li><a href="#all-products">All Products</a></li>
        </ul>
    </nav>
    <section id="best-sellers" class="product-section">
        <h2>Best Sellers</h2>
        <div class="product-slider">
            <!-- Repeat this div for each product in the slider -->
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Best Seller Product 1">
                <h3 data-product-name="Best Seller Product Name 1">Best Seller Product Name 1</h3>
                <p data-product-price="$29.99">$29.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=1" class="detail-link">View Details</a>
                <div class="rating">★★★★☆</div>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Best Seller Product 2">
                <h3 data-product-name="Best Seller Product Name 2">Best Seller Product Name 2</h3>
                <p data-product-price="$29.99">$29.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=2" class="detail-link">View Details</a>
                <div class="rating">★★★★☆</div>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Best Seller Product 3">
                <h3 data-product-name="Best Seller Product Name 3">Best Seller Product Name 3</h3>
                <p data-product-price="$29.99">$29.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=3" class="detail-link">View Details</a>
                <div class="rating">★★★★☆</div>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Best Seller Product 4">
                <h3 data-product-name="Best Seller Product Name 4">Best Seller Product Name 4</h3>
                <p data-product-price="$29.99">$29.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=4" class="detail-link">View Details</a>
                <div class="rating">★★★★☆</div>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Best Seller Product 5">
                <h3 data-product-name="Best Seller Product Name 5">Best Seller Product Name 5</h3>
                <p data-product-price="$29.99">$29.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=5" class="detail-link">View Details</a>
                <div class="rating">★★★★☆</div>
            </div>
        </div>   
    </section>
    <section id="all-products" class="product-section">
        <h2>All Products</h2>
        <div class="product-grid">
            <!-- Repeat this div for each product -->
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product 6">
                <h3 data-product-name="Product Name 6">Product Name 6</h3>
                <p data-product-price="$24.99">$24.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=6" class="detail-link">View Details</a>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product 7">
                <h3 data-product-name="Product Name 7">Product Name 7</h3>
                <p data-product-price="$24.99">$24.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=7" class="detail-link">View Details</a>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product 8">
                <h3 data-product-name="Product Name 8">Product Name 8</h3>
                <p data-product-price="$24.99">$24.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=8" class="detail-link">View Details</a>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product 9">
                <h3 data-product-name="Product Name 9">Product Name 9</h3>
                <p data-product-price="$24.99">$24.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=9" class="detail-link">View Details</a>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product 10">
                <h3 data-product-name="Product Name 10">Product Name 10</h3>
                <p data-product-price="$24.99">$24.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=10" class="detail-link">View Details</a>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product 11">
                <h3 data-product-name="Product Name 11">Product Name 11</h3>
                <p data-product-price="$24.99">$24.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=11" class="detail-link">View Details</a>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product 12">
                <h3 data-product-name="Product Name 12">Product Name 12</h3>
                <p data-product-price="$24.99">$24.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=12" class="detail-link">View Details</a>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Product 13">
                <h3 data-product-name="Product Name 13">Product Name 13</h3>
                <p data-product-price="$24.99">$24.99</p>
                <button>Add to Cart</button>
                <a href="productdetail.html?productId=13" class="detail-link">View Details</a>
            </div>
        </div>
    </section>
    <div id="notification" style="display: none; position: fixed; top: 20px; right: 20px; background-color: lightgreen; padding: 10px; border-radius: 5px;">Item added to cart!</div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script src="/Tech4All/JavaScript/product.js"></script>
</body>
</html>