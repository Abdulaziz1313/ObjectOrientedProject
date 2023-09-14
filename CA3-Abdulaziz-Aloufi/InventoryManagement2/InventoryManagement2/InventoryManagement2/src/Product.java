
public class Product {
	
	private int productID;
	private String productName;
	private int productCategory;
	private double productPrice;
	
	public Product(int productID, int productCategory, String productName, double productPrice) {
		this.productID = productID;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
	}
	
	public int getProductID() {
		return productID;
	}
	
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getProductCategory() {
		return productCategory;
	}
	
	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}
	
	public double getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	public String displayProduct() {
		return productID + " " + productName + " " + productCategory + 
				" " + productPrice;
	}
	
}
