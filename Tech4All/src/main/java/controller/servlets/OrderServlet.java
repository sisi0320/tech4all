package controller.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.Cart;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController databaseController = new DatabaseController();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Getting all cart items");
		 HttpSession session = request.getSession(false);
		 	String shippingAddress = request.getParameter("ship_address");
		 	String payMethod = request.getParameter("payMethod");
	        String username = (session != null) ? (String) session.getAttribute("username") : null;
	        LocalDate currentDate = LocalDate.now();
	        if (username == null) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to view the cart.");
	            return;
	        }
	        try {
	            List<Cart> cartItems = databaseController.getCartItems(username);
	            float total = databaseController.calculateTotal(cartItems);
	          
	            
	            databaseController.placeOrder(username, shippingAddress, payMethod, currentDate, total);
	            int orderid = databaseController.last_ordered(username);
	            System.out.println(orderid);
	            databaseController.insertOrderDetails(orderid, cartItems );
	            databaseController.clearCart(username);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching cart items: " + e.getMessage());
	        }
	}

}
