package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.StringUtils;

/**
 * Servlet implementation class ModificationServlet
 */
@WebServlet("/ModificationServlet")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("put triggered");
	
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete triggered");
		if (dbController.deleteProduct(req.getParameter(StringUtils.DELETE_ID)) == 1) {
			/*req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE); --> only works with forwardDispatcher*/
			req.getRequestDispatcher(StringUtils.PRODUCTLIST_PAGE).forward(req, resp);
		} else {
			/*req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);*/
			req.getRequestDispatcher(StringUtils.PRODUCTLIST_PAGE).forward(req, resp);
		}
	}

}
