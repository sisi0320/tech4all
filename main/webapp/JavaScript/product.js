 // Function to add product to the cart
    function addToCart(productId) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/Tech4All/CartServlet", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {  // Ensures the request is completed
                if (xhr.status === 200) {
                    alert("Product successfully added to cart!");
                } else {
                    alert("Failed to add product to cart! Status: " + xhr.status + ", Response: " + xhr.responseText);
                }
            }
        };
        xhr.send("action=add&productId=" + parseInt(productId) + "&quantity=1");
    }

    // Function to filter products by category
    function filterCategory() {
        var category = document.getElementById('categoryFilter').value;
        var items = document.querySelectorAll('.product-item');
        items.forEach(function(item) {
            item.style.display = (category === 'all' || item.getAttribute('data-category') === category) ? '' : 'none';
        });
    }

    // Function to open the details modal
    function openDetailsModal(modalId) {
        var modal = document.getElementById(modalId);
        if (modal) {
            modal.style.display = 'block';
        } else {
            console.error('Modal not found:', modalId);
        }
    }

    // Function to close the details modal
    function closeDetailsModal(modalId) {
        var modal = document.getElementById(modalId);
        if (modal) {
            modal.style.display = 'none';
        } else {
            console.error('Modal not found:', modalId);
        }
    }