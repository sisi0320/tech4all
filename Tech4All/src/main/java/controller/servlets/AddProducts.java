
 package controller.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DatabaseController;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class AddProducts
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.ADD_PRODUCTS})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB max size of file to be uploaded
maxRequestSize = 1024 * 1024 * 50) //multiple files could be uploaded

public class AddProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DatabaseController dbController = new DatabaseController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int productID = Integer.parseInt(request.getParameter("Prod_id"));
		
		String name = request.getParameter(StringUtils.NAME);
		String brand = request.getParameter(StringUtils.BRAND);
		float price = Float.parseFloat(request.getParameter(StringUtils.PRICE));
		String desc = request.getParameter(StringUtils.DESC);
		int stock = Integer.parseInt(request.getParameter(StringUtils.STOCK));
		String category = request.getParameter(StringUtils.CATEGORY);
		Part prodImg = request.getPart("image");
		
		ProductModel productModel = new ProductModel(productID, name, brand, price, desc, stock, category, prodImg);
		int result = dbController.addProducts(productModel);
		
		String savePath = StringUtils.IMAGE_DIR_SAVE_PROD;
	    String fileName = productModel.getProdImgUrl();
	    if(!fileName.isEmpty() && fileName != null)
	    	prodImg.write(savePath + fileName);
		
		if (result == 1) {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.ADD_PRODUCT_SUCCESS);
			request.getRequestDispatcher(StringUtils.ADMIN_PAGE).forward(request, response);
		}else if(result ==0) {
			//Redirect to the same register page with form data mistake
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ADD_PRODUCTS_ERROR );
			request.getRequestDispatcher(StringUtils.ADMIN_PAGE).forward(request, response);
		}else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.ADMIN_PAGE).forward(request, response);
		}
		
	}

}
