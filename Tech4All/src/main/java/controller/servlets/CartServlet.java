package controller.servlets;

import controller.database.DatabaseController;
import model.Cart;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController dbController;

    @Override
    public void init() {
        dbController = new DatabaseController();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String action = request.getParameter("action");
        if ("view".equals(action)) {
            displayCart(request, response);
        }
        
	
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	HttpSession userSession = request.getSession(false);
        String username = (userSession != null) ? (String) userSession.getAttribute("username") : null;
        if (username == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to view the cart.");
            return;
        }
        try {
            List<Cart> cartItems = dbController.getCartItems(username);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("/Pages/Cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching cart items: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addToCart(request, response);
            } else if ("update".equals(action)) {
                updateCart(request, response);
            } else if ("delete".equals(action)) {
                removeFromCart(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log("SQL or Class Not Found Exception: ", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database access error: " + e.getMessage());
        } catch (NumberFormatException e) {
            log("Number Format Exception: ", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        } catch (Exception e) {
            log("General Exception: ", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error: " + e.getMessage());
        }

        if (!response.isCommitted()) {
            response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp");
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false); // Get the existing session if one exists
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to add items to the cart.");
            return;
        }

        String productIdStr = request.getParameter("productId");
        if (productIdStr == null || productIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing.");
            return;
        }
        int productId = Integer.parseInt(productIdStr);
        String productName = dbController.getProductNameById(productId);  
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = dbController.getPriceByProductId(productId);
        if (price <= 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found or invalid price.");
            return;
        }

        Cart cart = new Cart(username, productId, productName, quantity, price);  // Updated to include productName
        boolean success = dbController.addCartItem(cart);
        if (!success) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add item to cart.");
        } else {
            response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp");
        }
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        boolean success = dbController.updateCartItem(cartId, quantity);
        if (!success) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update cart item.");
        } else {
            response.sendRedirect("CartServlet?action=view");  
        }
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int cartId = Integer.parseInt(request.getParameter("cartId"));

        boolean success = dbController.deleteCartItem(cartId);
        if (!success) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to remove cart item.");
        } else {
            response.sendRedirect("CartServlet?action=view");  
        }
    }

}
