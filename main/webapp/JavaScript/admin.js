
document.getElementById("openAdd").addEventListener("click", function() {
  document.getElementById("addWindow").style.display = "flex";
});

document.getElementById("closeAdd").addEventListener("click", function() {
  document.getElementById("addWindow").style.display = "none";
});



document.getElementById("myForm").addEventListener("submit", function(event) {
    // Prevent the default form submission
    event.preventDefault();

    // Log form submission
    console.log("Form is being submitted...");

    // Retrieve form data
    var formData = new FormData(this);

    // Log form data
    for (var pair of formData.entries()) {
        console.log(pair[0] + ': ' + pair[1]);
    }

    // Manually submit the form
    this.submit();
});
function openOrderDetails(orderId) {
    const detailContent = document.getElementById('detail-content');
    detailContent.innerHTML = `<strong>Order ID:</strong> ${orderId} <br> <strong>Status:</strong> Shipped <br> <strong>Total:</strong> $1200`;
    document.getElementById('modal').style.display = 'block';
}

function openUserDetails(userId) {
    const detailContent = document.getElementById('detail-content');
    detailContent.innerHTML = `<strong>User ID:</strong> ${userId} <br> <strong>Name:</strong> John Doe <br> <strong>Email:</strong> johndoe@example.com`;
    document.getElementById('modal').style.display = 'block';
}
 
// This function would be called when the "View Details" button is clicked.
function openOrderDetails(orderId) {
    // Fetch order details from the server using AJAX or use pre-loaded data
    // For now, let's assume you have the order details on the page already, just hidden.

    // This is where you would make an AJAX call, but for demonstration,
    // let's assume the data is already on the page as a JS object.
    // Suppose orderDetails is a JavaScript object containing details about orders
    const orderDetails = {
        '101': {
            date: '2024-04-15',
            status: 'Shipped',
            total: 1200
        },
        // ... other orders
    };

    const detailContent = document.getElementById('detail-content');
    const order = orderDetails[orderId.toString()]; // Convert orderId to string to use as key

    if (order) {
        detailContent.innerHTML = `
            <h3>Order Details</h3>
            <p><strong>Date:</strong> ${order.date}</p>
            <p><strong>Status:</strong> ${order.status}</p>
            <p><strong>Total:</strong> $${order.total}</p>
        `;
    } else {
        detailContent.innerHTML = '<p>Order details not available.</p>';
    }

    document.getElementById('modal').style.display = 'block';
}

