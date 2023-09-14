import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InventoryManager {
	
	private String statusMessage;
	private String driver;
	private String url;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public InventoryManager() {
	    this.statusMessage = "";
	    this.driver = "com.mysql.cj.jdbc.Driver";
	    this.url = "jdbc:mysql://localhost/InventoryManagement?characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	private boolean findCustomer(int ID) {
		boolean found = false;
		ArrayList<Customer> allCustomers = readCustomers();
		for(Customer c : allCustomers) {
			if(c.getCustomerID() == ID) {
				found = true;
			}
		}
		return found;
	}
	
	private boolean findProduct(int ID) {
		boolean found = false;
		ArrayList<Product> allProducts = readProducts();
		for(Product p : allProducts) {
			if(p.getProductID() == ID) {
				found = true;
			}
		}
		return found;
	}
	
	public Purchase findPurchase(int ID) {
		Purchase found = null;
		ArrayList<Purchase> allPurchases = readPurchases();
		for(Purchase r : allPurchases) {
			if(r.getPurchaseID() == ID) {
				found = r;
			}
		}
		return found;
	}
	
	
	public ArrayList<Customer> readCustomers(){
		try {
			ArrayList<Customer> customers = new ArrayList<Customer>();
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url, "root", "Aziz.1313");
		    String query = "SELECT * FROM Customer";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()){
		    	int ID = rs.getInt(1);
		    	String FName = rs.getString(2);
		    	String LName = rs.getString(3);
		    	String Address = rs.getString(4);
		    	String Phone = rs.getString(5);
		    	String Email = rs.getString(6);
		    	Customer c = new Customer(ID, FName, LName, Address, Phone, Email);
		    	customers.add(c);
		    }
		    return customers;
		}
		catch(Exception ex) {
			statusMessage = "Database Error " + ex.getMessage();
			return null;
		}
	}

	public ArrayList<Product> readProducts(){
		try {
			ArrayList<Product> products = new ArrayList<Product>();
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url, "root", "Aziz.1313");
		    String query = "SELECT * FROM Product";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()){
		    	int id = rs.getInt(1);
		    	int category = rs.getInt(2);
		    	String name = rs.getString(3);
		    	double price = rs.getDouble(4);
		    	Product p = new Product(id, category, name, price);
		    	products.add(p);
		    }
		    return products;
		}
		catch(Exception ex) {
			statusMessage = "Database Error " + ex.getMessage();
			return null;
		}
	}
	
	public ArrayList<Purchase> readPurchases(){
		try {
			ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url, "root", "Aziz.1313");
		    String query = "SELECT * FROM Purchase";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    while (rs.next()){
		    	int id = rs.getInt(1);
		    	int customer = rs.getInt(2);
		    	int product = rs.getInt(3);
		    	String status = rs.getString(4);
		    	Purchase r = new Purchase(id, customer, product, status);
		    	purchases.add(r);
		    }
		    return purchases;
		}
		catch(Exception ex) {
			statusMessage = "Database Error " + ex.getMessage();
			return null;
		}
	}
	
	public void addPurchase(int customer, int product) {
		if(findCustomer(customer) && findProduct(product)) {
			try {
				Class.forName(driver);
			    Connection conn = DriverManager.getConnection(url, "root", "Aziz.1313");
			    Statement st = conn.createStatement();
			    String query = "INSERT INTO Purchase VALUES (" + customer + ", " + product + ", 'Pending')";
				st.executeUpdate(query); 
				statusMessage = "A new purchase has been added successfully";
			}
			catch(Exception ex) {
				statusMessage = "Database Error " + ex.getMessage();
			}
		}
		else {
			statusMessage = "At least one parameter is unknown!"
					+ "Please double check your customer and product ID!";
		}
	}
	
	
	public void removePurchase(int purchaseID) {
		Purchase found = findPurchase(purchaseID);
		if(found != null) {
			if(found.getPurchaseStatus().equals("Processed")) {
				statusMessage = "Could not remove the already processed purchase!";
			}
			else {
				try {
					Class.forName(driver);
				    Connection conn = DriverManager.getConnection(url, "root", "Aziz.1313");
				    Statement st = conn.createStatement();
				    String query = "DELETE FROM Purchase WHERE PurchaseID = " + purchaseID;
				    st.executeUpdate(query); 
				    statusMessage = "Purchase with ID " + purchaseID + " removed successfully!";
				}
				catch(Exception ex) {
					statusMessage = "Database error " + ex.getMessage();
				}
			}
		}
		else {
			statusMessage = "Could not find purchase with ID = " + purchaseID;
		}
	}
	
	public void editPurchase(int purchaseID, int productID) {
		Purchase found = findPurchase(purchaseID);
		if(found != null) {
			if(found.getPurchaseStatus().equals("Processed")) {
				statusMessage = "Could not edit the already processed purchase!";
			}
			else if(!findProduct(productID)){
				statusMessage = "Could not find the product with ID = " + productID;
			}
			else {
				try {
					Class.forName(driver);
				    Connection conn = DriverManager.getConnection(url, "root", "Aziz.1313");
				    Statement st = conn.createStatement();
				    String query = "UPDATE Purchase SET ProductID = " + productID + " WHERE PurchaseID = " + purchaseID;
				    st.executeUpdate(query);
				    statusMessage = "Purchase with ID " + purchaseID + " edited successfully!";
				}
				catch(Exception ex) {
					statusMessage = "Database error " + ex.getMessage();
				}
			}
		}
		else {
			statusMessage = "Could not find purchase with ID = " + purchaseID;
		}
	}
}
