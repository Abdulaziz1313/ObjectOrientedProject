
public class Purchase {

	private int purchaseID;
	private int customerID;
	private int productID;
	private String purchaseStatus;
	
	
	public Purchase(int purchaseID, int customerID, 
			int productID, String status) {
		this.purchaseID = purchaseID;
		this.customerID = customerID;
		this.productID = productID;
		this.purchaseStatus = status;
	}
	
	public int getPurchaseID() {
		return purchaseID;
	}
	
	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getProductID() {
		return productID;
	}
	
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	
	public String getPurchaseStatus() {
		return purchaseStatus;
	}
	
	public void setPurchaseStatus(String status) {
		this.purchaseStatus = status;
	}
	
	public String toString() {
		return purchaseID + " " + customerID + " " + productID;
	}
	
}
