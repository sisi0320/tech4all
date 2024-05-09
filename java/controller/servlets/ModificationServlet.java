package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class ModificationServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = "/ModificationServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB max size of file to be uploaded
maxRequestSize = 1024 * 1024 * 50) //multiple files could be uploaded

public class ModificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final controller.database.DatabaseController dbController;
    
    public ModificationServlet() {
    	 this.dbController = new controller.database.DatabaseController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String prodid = request.getParameter("productID");
		int prodID = Integer.parseInt(prodid);
		System.out.println("This is the id" + prodID);
		ProductModel product = dbController.getProductDetail(prodID);
		 if (product != null) {
		        System.out.println("Product details retrieved: " + product.toString()); // Debug statement
		    } else {
		        System.out.println("Product details not found for ID: " + prodID); // Debug statement
		    }
		request.setAttribute("product", product);
		request.getRequestDispatcher("/Pages/ProductUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post triggered");
		
		String editID = request.getParameter(StringUtils.EDIT_ID);
		String deleteID = request.getParameter(StringUtils.DELETE_ID);
		
		if (editID != null && !editID.isEmpty()) {
			doPut(request, response);
		}
		if (deleteID != null && !deleteID.isEmpty()) {
			doDelete(request, response);
		}
		

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("put triggered");
		String prodId = req.getParameter("productID");
		
		int productID = Integer.parseInt(prodId);
		
		String name = req.getParameter(StringUtils.NAME);
		String brand = req.getParameter(StringUtils.BRAND);
		float price = Float.parseFloat(req.getParameter(StringUtils.PRICE));
		String desc = req.getParameter(StringUtils.DESC);
		int stock = Integer.parseInt(req.getParameter(StringUtils.STOCK));
		String category = req.getParameter(StringUtils.CATEGORY);
		Part prodImg = req.getPart("image");
		
		ProductModel productModel = new ProductModel(productID, name, brand, price, desc, stock, category, prodImg);
		int result = dbController.updateProduct(productModel);
		String savePath = StringUtils.IMAGE_DIR_SAVE_PROD;
	    String fileName = productModel.getProdImgUrl();
	    if(!fileName.isEmpty() && fileName != null)
	    	prodImg.write(savePath + fileName);
		
		if (result ==1){
			req.getRequestDispatcher(StringUtils.PROD_LIST_SERVLET).forward(req, resp);
		}else {
			req.getRequestDispatcher(StringUtils.PROD_LIST_SERVLET).forward(req,  resp);
		}
		
		
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		if (dbController.deleteProduct(req.getParameter(StringUtils.DELETE_ID)) == 1) {
			/*req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE); --> only works with forwardDispatcher*/
			req.getRequestDispatcher(StringUtils.PROD_LIST_SERVLET).forward(req, resp);
		} else {
			/*req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);*/
			req.getRequestDispatcher(StringUtils.PROD_LIST_SERVLET).forward(req, resp);
		}
	}

}
