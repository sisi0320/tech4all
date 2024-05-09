package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.OrderModel;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class OrdersList
 */
@WebServlet("/OrdersList")
public class OrdersList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController databaseController = new DatabaseController(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Getting all Orders");
		List<OrderModel> ordersList = databaseController.getAllOrders();
		
		request.setAttribute("ordersList", ordersList);
		request.getRequestDispatcher("/Pages/OrderList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post triggered");
		String orderID = request.getParameter("orderId");
		
		int orderId = Integer.parseInt(orderID);
		System.out.println(orderId);
		String status = request.getParameter("status");
		databaseController.updateOrderStatus(orderId, status);
	}

}
