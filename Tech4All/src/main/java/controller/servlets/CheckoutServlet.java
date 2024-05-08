package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.Cart;
import model.ProductModel;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController databaseController = new DatabaseController();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Getting all cart items");
		 HttpSession session = request.getSession(false);
	        String username = (session != null) ? (String) session.getAttribute("username") : null;
	        if (username == null) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to view the cart.");
	            return;
	        }
	        try {
	            List<Cart> cartItems = databaseController.getCartItems(username);
	            request.setAttribute("cartItems", cartItems);
	            float eachtotal = databaseController.calculateEach(cartItems);
	            float total = databaseController.calculateTotal(cartItems);

		        request.setAttribute("total", total);
		        request.setAttribute("eachtotal", eachtotal);
	            request.getRequestDispatcher("/Pages/Checkout.jsp").forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching cart items: " + e.getMessage());
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	       
	}

}
