// Function to add products to a cart with notification
function addToCart(productName, price) {
    const notification = document.getElementById('notification');
    notification.textContent = `Added ${productName} costing ${price} to your cart!`;
    notification.style.display = 'block';
    notification.style.opacity = 1;
    setTimeout(() => {
        notification.style.opacity = 0;
        setTimeout(() => notification.style.display = 'none', 500); // Wait for fade out
    }, 2000); // Show notification for 2 seconds
}

// Event delegation for Add to Cart buttons
document.addEventListener('DOMContentLoaded', function() {
    const productGrid = document.querySelector('.product-grid');
    const productSlider = document.querySelector('.product-slider');
    
    function handleAddToCart(event) {
        if (event.target.tagName === 'BUTTON') {
            const card = event.target.closest('.product-card');
            const productName = card.querySelector('[data-product-name]').textContent;
            const price = card.querySelector('[data-product-price]').textContent;
            addToCart(productName, price);
        }
    }

    if (productGrid) {
        productGrid.addEventListener('click', handleAddToCart);
    }
    
    if (productSlider) {
        productSlider.addEventListener('click', handleAddToCart);
    }
});

// Initialize Slick Slider for the Best Sellers section
$(document).ready(function(){
    $('.product-slider').slick({
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2
                }
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 1
                }
            }
        ]
    });
});


document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('productId'); // Get product ID from URL

    // Now, fetch the product data using this productId
    fetchProductDetails(productId);
});

function fetchProductDetails(productId) {
    // Simulate fetching product data
    const productData = {
        id: productId,
        title: "Sample Product " + productId,
        price: 29.99,
        description: "This is a sample description of the product " + productId,
        imageUrl: "https://via.placeholder.com/500"
    };

    // Update the page with this data
    document.getElementById('product-title').textContent = productData.title;
    document.getElementById('product-price').textContent = `Price: $${productData.price}`;
    document.getElementById('product-description').textContent = productData.description;
    document.getElementById('product-image').src = productData.imageUrl;
    document.getElementById('product-image').alt = productData.title;
}


function createProductCard(product) {
    return `
        <div class="product-card">
            <img src="${product.imageUrl}" alt="${product.name}">
            <h3 data-product-name="${product.name}">${product.name}</h3>
            <p data-product-price="$${product.price}">$${product.price}</p>
            <button>Add to Cart</button>
            <a href="productdetail.html?productId=${product.id}" class="detail-link">View Details</a>
            <div class="rating">${product.rating}</div>
        </div>
    `;
}


