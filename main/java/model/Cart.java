package model;

public class Cart {
    private int cartId;
    private String userId;
    private int productId;
    private String productName; // Add this line
    private int quantity;
    private float price;

    // Update the constructor to include productName
    public Cart(String userId, int productId, String productName, int quantity, float price) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName; // Set the product name
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) { 
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
