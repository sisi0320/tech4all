package model;

import java.io.File;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtils;

public class ProductModel {
	private int Prod_id;
	private String Prod_name;
	private String brand;
	private float price;
	private String description;
	private int stock;
	private String Category;
	private String prodImgUrl;

public ProductModel(int Prod_id, String Prod_name, String brand, float price, String description, int stock, String Category, Part prodImgUrl){
	this.Prod_id = Prod_id;
	this.Prod_name = Prod_name;
	this.brand = brand;
	this.price = price;
	this.description = description;
	this.stock = stock;
	this.Category = Category;
	this.prodImgUrl = getImageUrl(prodImgUrl);
}
public ProductModel() {
	
}
public int getProd_id() {
	return Prod_id;
}
public void setProd_id(int prod_id) {
	Prod_id = prod_id;
}

public String getProd_name() {
	return Prod_name;
}
public void setProd_name(String prod_name) {
	Prod_name = prod_name;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}

public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}

public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}

public String getCategory() {
	return Category;
}
public void setCategory(String category) {
	Category = category;
}

private String getImageUrl(Part part) {
	String savePath = StringUtils.IMAGE_DIR_SAVE_PROD;
	File fileSaveDir = new File(savePath);
	String imageUrlFromPart = null;
	if (!fileSaveDir.exists()) {
		fileSaveDir.mkdirs();
	}
	String contentDisp = part.getHeader("content-disposition");
	String[] items = contentDisp.split(";");
	for (String s : items) {
		if (s.trim().startsWith("filename")) {
			imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
		}
	}
	if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
		imageUrlFromPart = "download.jpg";
	}
	return imageUrlFromPart;
}

public String getProdImgUrl() {
	return prodImgUrl;
}
public void setProdImgUrl(Part prodImgUrl) {
	this.prodImgUrl = getImageUrl(prodImgUrl);
}

public void setProdUrlFromDB(String imageUrl) {
	this.prodImgUrl = imageUrl;
}
}