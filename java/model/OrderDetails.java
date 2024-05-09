package model;

public class OrderDetails {
	private int detailId;
	 private int orderId;
	 private int productId;
	 private float price; // Add this line
	 private int quantity;
	 private String prod_name;
	 
	 public OrderDetails(int detailId,int orderId, int productId, float price, int quantity){
			this.detailId = detailId;
			this.orderId = orderId;
			this.productId = productId;
			this.price = price;
			this.quantity = quantity;
		 }
	 public OrderDetails(int detailId,String prod_name, float price, int quantity) {
		 this.detailId = detailId;
			this.prod_name = prod_name;
			this.price = price;
			this.quantity = quantity;
	 }
		 public OrderDetails(){
			 
		 }
		 
		 public int getDetailId() {
			return detailId;
		}
		 public void setDetailId(int detailId) {
			this.detailId = detailId;
		}
		 
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		public String getProd_name() {
			return prod_name;
		}
		public void setProd_name(String prod_name) {
			this.prod_name = prod_name;
		}
}
