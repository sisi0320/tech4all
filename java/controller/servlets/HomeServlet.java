package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductModel;
import controller.database.DatabaseController;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController databaseController = new DatabaseController(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		DatabaseController dbController = new DatabaseController();
        ArrayList<ProductModel> products = dbController.getAllProductDetails();
        ArrayList<ProductModel> previewProducts;

        if (products.size() >= 4) {
            previewProducts = new ArrayList<>(products.subList(0, 5));
        } else {
            previewProducts = new ArrayList<>(products); // If less than 4 products, just copy all
        }
        request.setAttribute("previewProducts", previewProducts);
        System.out.println("Products Found: " + previewProducts.size());
        request.getRequestDispatcher("/Pages/Home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		String searchQuery = request.getParameter("search_query");
	     String searchCriteria = request.getParameter("search_criteria");
	        
	        // Perform search based on the selected criteria
	        if ("brand".equals(searchCriteria)) {
	            // Search by brand
	            ArrayList<ProductModel> productsList = databaseController.searchByBrand(searchQuery);
	            // Set products attribute to be displayed in JSP
	            
	            request.setAttribute("productsList", productsList);
	            request.getRequestDispatcher("/Pages/SearchedProducts.jsp").forward(request, response);
	            
	        } else if ("name".equals(searchCriteria)) {
	            // Search by price
	            List<ProductModel> productsList = databaseController.searchByName(searchQuery);
	            // Set products attribute to be displayed in JSP
	            request.setAttribute("productsList", productsList);
	            request.getRequestDispatcher("/Pages/SearchedProducts.jsp").forward(request, response);
	        }
	}

}
