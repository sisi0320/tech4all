package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.OrderDetails;
import model.OrderModel;
import model.PasswordEncryptionWithAes;
import model.ProductModel;
import model.UserModel;
import util.StringUtils;

public class DatabaseController {
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/tech4all";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
		
	}
	//To add new users
	public int addUsers(UserModel userModel) {
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_USERS);
			
			st.setString(1, userModel.getFirstName());
			st.setString(2,userModel.getLastName());
			st.setString(3, userModel.getUsername());
			st.setDate(4, Date.valueOf(userModel.getDob()));
			st.setString(5, userModel.getGender());
			st.setString(6, userModel.getPhoneNumber());
			st.setString(7, userModel.getEmail());
			st.setString(8, userModel.getAddress());
			st.setString(9, PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword()));
			st.setString(10, userModel.getPfpUrl());
			int result = st.executeUpdate();
			return result > 0 ? 1: 0;
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
		}
	//Getting user info for login process
	public int getUsersLoginInfo(String username, String password) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USERS_INFO_LOGIN);
			st.setString(1,username);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				//username and password match in the database
				String userDb = rs.getString("username");
				String passwordDb = rs.getString("password");
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);
				
				if (decryptedPwd != null && userDb.equals(username) && decryptedPwd.equals(password)) {
					return 1;
				}else {
					return 0;
				}
			}else {
				//no matching record found
				return 2;
			}	
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	//Method to get a user's role
	public String getUserRoles(String username) {
		String userRole = null;
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USERS_ROLE);
			st.setString(1,username);
			
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				userRole = rs.getString("Roles");
				
			}
	}catch(SQLException | ClassNotFoundException e) {
		 e.printStackTrace();
	}
		return userRole;
	}
	
	//to check if username already exists
	public int checkUsername(String username) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.CHECK_USERNAME);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else {
				return 0;
			}
		}catch(SQLException| ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	//to check if mail id already exists
	public int checkMail(String mail) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.CHECK_MAIL);
			st.setString(1, mail);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else {
				return 0;
			}
		}catch(SQLException| ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	//to check if phone number already exists
	public int checkNumber(String number) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.CHECK_NUMBER);
			st.setString(1, number);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else {
				return 0;
			}
		}catch(SQLException| ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	//list of all users
	public ArrayList<UserModel> getAllUsersInfo(){
		try {
			PreparedStatement stmt = getConnection()
					.prepareStatement("SELECT * FROM users");
			ResultSet result = stmt.executeQuery();
			
			ArrayList<UserModel> users = new ArrayList<UserModel>();
			
			while(result.next()) {
				UserModel user = new UserModel();
				user.setFirstName(result.getString("First_Name"));
				user.setLastName(result.getString("Last_Name"));
				user.setDob(result.getDate("DOB").toLocalDate());
				user.setEmail(result.getString("Mail"));
				user.setGender(result.getString("Gender"));
				user.setPhoneNumber(result.getString("Phone"));
				user.setAddress(result.getString("Address"));
				user.setUsername(result.getString("Username"));
				
				System.out.println(user.getFirstName());
				users.add(user);
			}
			return users;
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//To get a particular user's detail
	public UserModel getUserDetail(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_USER_DETAIL);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				UserModel user = new UserModel();
				user.setUsername(rs.getString("Username"));
				user.setFirstName(rs.getString("First_Name"));
				user.setLastName(rs.getString("Last_Name"));
				user.setDob((rs.getDate("DOB")).toLocalDate());
				user.setGender(rs.getString("Gender"));
				user.setAddress(rs.getString("Address"));
				user.setPhoneNumber(rs.getString("Phone"));
				user.setEmail(rs.getString("Mail"));
				user.setPfpFromDB(rs.getString("ProfileImg"));
				
				System.out.println(user.getUsername());
				System.out.println(user.getFirstName());
				System.out.println(user.getLastName());
				System.out.println(user.getDob());
				System.out.println(user.getGender());
				System.out.println(user.getAddress());
				System.out.println(user.getPhoneNumber());
				System.out.println(user.getEmail());
				System.out.println(user.getPfpUrl());
				return user;
			}else {
				return null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return null;
		}
	}
	
	//To update user info
	public int updateUserInfo(UserModel usermodel) { 
		 try( Connection con = getConnection()){
		  PreparedStatement st =con.prepareStatement(StringUtils.UPDATE_USER_INFO);
		  st.setString(1,usermodel.getFirstName()); 
		  st.setString(2, usermodel.getLastName());
		  st.setDate(4, Date.valueOf(usermodel.getDob()));
		  st.setString(4,usermodel.getGender());
		  st.setString(5, usermodel.getPhoneNumber());
		  st.setString(6, usermodel.getEmail());
		  st.setString(7,usermodel.getAddress());
		  st.setString(8, usermodel.getPfpUrl());
		  st.setString(9, usermodel.getUsername());
		  
		  return st.executeUpdate();
		}catch (SQLException | ClassNotFoundException ex){
			ex.printStackTrace(); 
			return -1; } 
		 }

	//To change and update password
	public int updatePassword(String username, String password, String newPassword) {
		try(Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement("SELECT Password FROM users WHERE Username = ?");
			st.setString(1,username);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				String passwordDb = rs.getString("password");
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);
				
				if (decryptedPwd != null && decryptedPwd.equals(password)) {
					 String encryptedNewPassword = PasswordEncryptionWithAes.encrypt(username, newPassword);
					PreparedStatement st2 = con.prepareStatement("UPDATE users SET Password = ? WHERE Username=?");
					st2.setString(1, encryptedNewPassword);
					st2.setString(2,username);
					int rowsUpdated = st2.executeUpdate();
	                if (rowsUpdated > 0) {
	                    return 1; // Success
	                } else {
	                    return 0; // No rows updated
	                }
	            } else {
	                return -2; // Incorrect old password
	            }
	        } else {
	            return -3; // User not found
	        }
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	//To add new products to the database
	public int addProducts(ProductModel productModel) {
		
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_PRODUCTS);
			
			st.setInt(1, productModel.getProd_id());
			st.setString(2,productModel.getProd_name());
			st.setString(3, productModel.getBrand());
			st.setFloat(4, productModel.getPrice());
			st.setString(5, productModel.getDescription());
			st.setInt(6, productModel.getStock());
			st.setString(7, productModel.getCategory());
			st.setString(8, productModel.getProdImgUrl());
			
			int result = st.executeUpdate();
			return result > 0 ? 1: 0;
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
		}
	
	//list for all products
	public ArrayList<ProductModel> getAllProductDetails(){
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement("SELECT * FROM products");
			
			
			ResultSet result = st.executeQuery();
			
			ArrayList<ProductModel> products = new ArrayList<ProductModel>();
			
			while(result.next()) {
				ProductModel product = new ProductModel();
				product.setProd_id(result.getInt("ProductID"));
				product.setProd_name(result.getString("Name"));
				product.setBrand(result.getString("Brand"));
				product.setPrice(result.getFloat("Price"));
				product.setDescription(result.getString("Description"));
				product.setStock(result.getInt("Stock"));
				product.setCategory(result.getString("Category"));
				product.setProdUrlFromDB(result.getString("P_Image"));
				
				
				products.add(product);
			}
			return products;
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//method that deletes a product 
	public int deleteProduct(String prod_id) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_PRODUCT);
			st.setString(1, prod_id);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	public ProductModel getProductDetail(int prod_id) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.GET_PRODUCT_DETAILS);
			st.setInt(1, prod_id);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				ProductModel product = new ProductModel();
				product.setProd_id(rs.getInt("ProductID"));
				product.setProd_name(rs.getString("Name"));
				product.setBrand(rs.getString("Brand"));
				product.setPrice(rs.getFloat("Price"));
				product.setDescription(rs.getString("Description"));
				product.setStock(rs.getInt("Stock"));
				product.setCategory(rs.getString("Category"));
				product.setProdUrlFromDB(rs.getString("P_Image"));
				
				return product;
			}else {
				return null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return null;
		}
	}
	
	//update stock
	public void updateStock(int productId, int quantity) throws SQLException, ClassNotFoundException {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("UPDATE products SET stock = stock - ? WHERE productID = ?");
			st.setInt(1, quantity);
			st.setInt(2, productId);
			int rowsAffected = st.executeUpdate();
	        if (rowsAffected != 1) {
	            throw new SQLException("Failed to update stock for product with ID: " + productId);
	        }
		}catch (SQLException e) {
	            e.printStackTrace();
	             // Re-throwing the exception for handling at a higher level
	        }
			
	}
	
	//method to update a prodct's details
	  public int updateProduct(ProductModel productmodel) { 
		 try( Connection con = getConnection()){
		  PreparedStatement st =con.prepareStatement(StringUtils.UPDATE_PRODUCT);
		  st.setString(1,productmodel.getProd_name()); 
		  st.setString(2, productmodel.getBrand());
		  st.setFloat(3, productmodel.getPrice());
		  st.setString(4,productmodel.getDescription());
		  st.setInt(5, productmodel.getStock());
		  st.setString(6, productmodel.getCategory());
		  st.setString(7,productmodel.getProdImgUrl());
		  st.setInt(8, productmodel.getProd_id());
		  return st.executeUpdate();
		}catch (SQLException | ClassNotFoundException ex){
			ex.printStackTrace(); 
			return -1; } }
	 

//Add an item to the cart
	public boolean addCartItem(Cart cart) throws SQLException, Exception {
	    String sql = "INSERT INTO cart (user_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
	    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, cart.getUserId()); 
	        ps.setInt(2, cart.getProductId());
	        ps.setInt(3, cart.getQuantity());
	        ps.setDouble(4, cart.getPrice());
	        int result = ps.executeUpdate();
	        return result > 0;
	    }
	}

	// Update the quantity of an item in the cart
	public boolean updateCartItem(int cartId, int quantity) throws SQLException, Exception {
	    String sql = "UPDATE cart SET quantity = ? WHERE cart_id = ?";
	    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, quantity);
	        ps.setInt(2, cartId);
	        int result = ps.executeUpdate();
	        return result > 0;
	    }
	}

	// Delete an item from the cart
	public boolean deleteCartItem(int cartId) throws SQLException, Exception {
	    String sql = "DELETE FROM cart WHERE cart_id = ?";
	    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, cartId);
	        int result = ps.executeUpdate();
	        return result > 0;
	    }
	}

	// Retrieve all cart items for a user
	public List<Cart> getCartItems(String username) throws SQLException, Exception {
	    List<Cart> cartItems = new ArrayList<>();
	 
	    String sql = "SELECT c.cart_id, c.user_id, c.product_id, p.Name as product_name, p.P_Image , c.quantity, c.price FROM cart c JOIN products p ON c.product_id = p.ProductID WHERE c.user_id = ?";
	    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, username);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Cart item = new Cart(
	                rs.getString("user_id"),
	                rs.getInt("product_id"), 
	                rs.getString("product_name"), 
	                rs.getInt("quantity"), 
	                rs.getInt("price") 
	            );
	            item.setCartId(rs.getInt("cart_id")); 
	            cartItems.add(item);
	        }
	        return cartItems;
	    }
	}




	// Fetch product price by product ID
	public float getPriceByProductId(int productId) throws SQLException, ClassNotFoundException {
	    try (Connection con = getConnection()) {
	        PreparedStatement ps = con.prepareStatement("SELECT Price FROM products WHERE ProductID = ?");
	        ps.setInt(1, productId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getFloat("Price");
	        } else {
	            return  -1.0f; 
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	

	
	
	//search a product by brand
	public ArrayList<ProductModel> searchByBrand(String brand){
		
		 try (Connection con = getConnection()) {
		        PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE Brand LIKE ?");
		        ps.setString(1, brand);
		        ResultSet rs = ps.executeQuery();
		        ArrayList<ProductModel> products = new ArrayList<ProductModel>();
		        
		        while (rs.next()) {
		            // Create Product object from result set
		            ProductModel product = new ProductModel();
		           
		            product.setProd_id(rs.getInt("ProductID"));
					product.setProd_name(rs.getString("Name"));
					product.setBrand(rs.getString("Brand"));
					product.setPrice(rs.getFloat("Price"));
					product.setDescription(rs.getString("Description"));
					product.setStock(rs.getInt("Stock"));
					product.setCategory(rs.getString("Category"));
					product.setProdUrlFromDB(rs.getString("P_Image"));
					
					
		            // Add product to list
		            products.add(product);
		        }
		        return products;
		       
		    }   catch (SQLException  | ClassNotFoundException ex) {
		    	ex.printStackTrace();
				return null;
		    }
		 
	}
	
	//search a product by price
	public ArrayList<ProductModel> searchByName(String name){
		
		 try (Connection con = getConnection()) {
		        PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE Name LIKE ?");
		        ps.setString(1, name);
		        ResultSet rs = ps.executeQuery();
		        ArrayList<ProductModel> products = new ArrayList<ProductModel>();
		        
		        while (rs.next()) {
		            // Create Product object from result set
		            ProductModel product = new ProductModel();
		            product.setProd_id(rs.getInt("ProductID"));
		            product.setProd_name(rs.getString("Name"));
		            product.setBrand(rs.getString("Brand"));
		            product.setPrice(rs.getFloat("Price"));;
		            product.setStock(rs.getInt("Stock"));
		            product.setDescription(rs.getString("Description"));
		            
		            // Add product to list
		            products.add(product);
		        }
		        return products;
		       
		    }   catch (SQLException  | ClassNotFoundException ex) {
		    	ex.printStackTrace();
				return null;
		    }
		 
	}
	
	
	// Fetch product name by product ID
	public String getProductNameById(int productId) throws SQLException, ClassNotFoundException {
	    try (Connection con = getConnection()) {
	        PreparedStatement ps = con.prepareStatement("SELECT Name FROM products WHERE ProductID = ?");
	        ps.setInt(1, productId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getString("Name");  
	        } else {
	            return null;  
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	
	
	public float calculateTotal(List<Cart> cartItems) {
        float total = 0.0f;
        for (Cart item : cartItems) {
            int quantity = item.getQuantity();
            float price = item.getPrice();
            float subtotal = quantity * price;
            total += subtotal;
        }
        return total;
    }
	
	//Adding an order
	public int placeOrder(String username, String shippingAddress, String payMethod, LocalDate currentDate, float total) {
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement("INSERT INTO orders" + "(orderdate, ship_address, pay_method, User, grandTotal)"+ "VALUES(?, ?, ?, ?, ?)");
			st.setDate(1,Date.valueOf(currentDate));
			st.setString(2,shippingAddress);
			st.setString(3, payMethod);
			st.setString(4, username);
			st.setFloat(5, total);
			
			int result = st.executeUpdate();
			return result > 0 ? 1: 0;
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	//getting the order id of the last entered data to insert details into the orderdetails table
	public int last_ordered( String username){
		int orderId = 0;
		try (Connection con = getConnection()){
	    PreparedStatement st = con.prepareStatement(
	        "SELECT MAX(order_id) AS last_order_id FROM orders WHERE User = ?"
	    );
	    st.setString(1, username);
	    ResultSet rs = st.executeQuery();
	    if (rs.next()) {
	        orderId = rs.getInt("last_order_id");
	    }
	    return orderId;
	}catch(SQLException | ClassNotFoundException ex) {
		ex.printStackTrace();
		return -1;
	}
		}
	//Inserting cart items to the orderdetails.
	public int insertOrderDetails( int orderid, List<Cart> cartItems) {
		  try (Connection con = getConnection()) {
		        PreparedStatement st = con.prepareStatement(
		            "INSERT INTO orderdetails (orderID, productID, Price, Quantity) VALUES (?, ?, ?, ?)"
		        );
		        for (Cart item : cartItems) {
		            st.setInt(1, orderid);
		            st.setInt(2, item.getProductId());
		            st.setFloat(3, item.getPrice());
		            st.setInt(4, item.getQuantity());
		            st.executeUpdate(); // Execute the statement for each item
		        }
		        return cartItems.size(); // Return the number of items inserted
		    } catch (SQLException | ClassNotFoundException ex) {
		        ex.printStackTrace();
		        return -1;
		    }
	}
	
	//clearing the cart after placing orders
	public int clearCart(String username) {
		try( Connection con = getConnection()){
			  PreparedStatement st =con.prepareStatement("DELETE FROM cart WHERE user_id = ?");
			  st.setString(1, username);
			  int result = st.executeUpdate();
				return result > 0 ? 1: 0;
			}catch(SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return -1;
			}
	}
	
	//Getting all the orders from the order table
	public ArrayList<OrderModel> getAllOrders(){
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement("SELECT * FROM orders");
			
			
			ResultSet result = st.executeQuery();
			
			ArrayList<OrderModel> orders = new ArrayList<OrderModel>();
			
			while(result.next()) {
				OrderModel order = new OrderModel();
				order.setOrderId(result.getInt("order_id"));
				order.setUsername(result.getString("User"));
				order.setDate(result.getDate("orderdate").toLocalDate());
				order.setAddress(result.getString("ship_address"));
				order.setTotal(result.getFloat("grandTotal"));
				order.setPayment(result.getString("pay_method"));
				order.setStatus(result.getString("status"));
				
				System.out.println(order.getOrderId());
				System.out.println(order.getUsername());
				System.out.println(order.getDate());
				System.out.println(order.getAddress());
				System.out.println(order.getTotal());
				System.out.println(order.getPayment());
				System.out.println(order.getStatus());
				orders.add(order);
			}
			return orders;
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//updating the order status; called from admin page
	public int updateOrderStatus(int orderid, String status) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("UPDATE orders SET status=? WHERE order_id=?");
			st.setString(1, status);
			st.setInt(2, orderid);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	//retrieve orders of a user
	public List<OrderModel> getUsersOrders(String username) throws SQLException, Exception {
	    List<OrderModel> orders = new ArrayList<>();
	 
	    String sql = "SELECT order_id, orderdate, ship_address, grandTotal, pay_Method FROM orders WHERE User=?";
	    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, username);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            OrderModel order = new OrderModel(
	                rs.getInt("order_id"),
	                rs.getDate("orderdate").toLocalDate(), 
	                rs.getString("ship_address"), 
	                rs.getString("pay_Method"),
	                rs.getFloat("grandTotal")
	                 
	            );
	            System.out.println(order.getOrderId());
				System.out.println(order.getDate());
				System.out.println(order.getAddress());
				System.out.println(order.getTotal());
				System.out.println(order.getPayment());
				
	            orders.add(order);
	        }
	        return orders;
	    }
	}
	public List<OrderDetails> getOrderDetails(int orderID) throws SQLException, Exception {
	    List<OrderDetails> details = new ArrayList<>();
	 
	    String sql = "SELECT od.detailID, od.Price, od.Quantity, p.Name FROM orderdetails AS od, products AS p WHERE od.productID = p.ProductID AND od.orderID=?";
	    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, orderID);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            OrderDetails detail = new OrderDetails(
	                rs.getInt("detailID"),
	                rs.getString("Name"), 
	                rs.getFloat("Price"), 
	                rs.getInt("Quantity")
	                 
	            );
	            System.out.println(detail.getDetailId());
				System.out.println(detail.getProd_name());
				System.out.println(detail.getPrice());
				System.out.println(detail.getQuantity());
				
				details.add(detail);
	        }
	        return details;
	    }
	}

}

