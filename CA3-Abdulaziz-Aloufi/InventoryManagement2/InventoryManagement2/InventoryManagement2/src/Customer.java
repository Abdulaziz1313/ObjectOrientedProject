
public class Customer {
	
	private int customerID;
	private String customerFName;
	private String customerLName;
	private String customerAddress;
	private String customerPhone;
	private String customerEmail;
	
	public Customer(int customerID, String customerFName, String customerLName, String customerAddress,
			String customerPhone, String customerEmail) {
		super();
		this.customerID = customerID;
		this.customerFName = customerFName;
		this.customerLName = customerLName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerFName() {
		return customerFName;
	}

	public void setCustomerFName(String customerFName) {
		this.customerFName = customerFName;
	}

	public String getCustomerLName() {
		return customerLName;
	}

	public void setCustomerLName(String customerLName) {
		this.customerLName = customerLName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public String displayCustomer() {
		return customerID + " " + 
				customerLName + " " + customerFName + " " +
				customerAddress + " " + customerPhone +  " " + 
				customerEmail;
	}
	

}
