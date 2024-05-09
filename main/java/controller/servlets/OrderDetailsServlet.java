package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.OrderDetails;
import model.OrderModel;

/**
 * Servlet implementation class OrderDetailsServlet
 */
@WebServlet("/OrderDetailsServlet")
public class OrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController databaseController = new DatabaseController();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get"); 
		String orderID = request.getParameter("orderId"); // Assuming username is stored in session
		System.out.println(orderID);
		int id = Integer.parseInt(orderID);
	        List<OrderDetails> orderdetails;
			try {
				orderdetails = databaseController.getOrderDetails(id);
				request.setAttribute("orderdetails", orderdetails);
				System.out.println("orders Found: " + orderdetails.size());
				Boolean showPopup = true; // Assuming it's always true for debugging
				request.setAttribute("showPopup", showPopup);
				System.out.println("showPopup attribute set to: " + showPopup);
				
		        request.getRequestDispatcher("/Pages/Orderdetails.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Implement this method in your DAO
	        
	        // Pass the orders to the JSP for rendering
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
