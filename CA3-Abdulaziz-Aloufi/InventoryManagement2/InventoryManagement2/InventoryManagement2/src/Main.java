import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Main{    
	JFrame fr;    
	InventoryManager manager;
	Main(){    
		fr = new JFrame("IMS | Inventory Management System");   
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		manager = new InventoryManager();
		ArrayList<Customer> customers = manager.readCustomers();
		ArrayList<Product> products = manager.readProducts();
		ArrayList<Purchase> purchases = manager.readPurchases();
		
		if(customers == null || products == null || purchases == null) {
			 JOptionPane.showMessageDialog(new JFrame(),"Error reading data!\n"
	    			  + "(" + manager.getStatusMessage(), "Error",
	    		        JOptionPane.ERROR_MESSAGE);
		}
		else {
			JLabel empty1 = new JLabel("");
			JLabel title = new JLabel("  Welcome to the IMS!");
			title.setFont(new Font("Arial", Font.BOLD, 24));
			JLabel empty2 = new JLabel("");
			
			String[] cust = new String[customers.size()];
			String[] prod = new String[products.size()];
			String[] purch = new String[purchases.size()];
			
			for(int i = 0; i < customers.size(); i++) {
				cust[i] = customers.get(i).getCustomerID() + " " + customers.get(i).getCustomerFName() + " " + customers.get(i).getCustomerLName();
			}
			
			for(int i = 0; i < products.size(); i++) {
				prod[i] = products.get(i).getProductID() + " " + products.get(i).getProductName() + " " + String.format("%.2f", products.get(i).getProductPrice());
			}
			
			for(int i = 0; i < purchases.size(); i++) {
				purch[i] = purchases.get(i).getPurchaseID() + " " + purchases.get(i).getCustomerID();
			}
			
			JList lstCustomers = new JList(cust);
			lstCustomers.setFont(new Font("Arial", Font.PLAIN, 14));
			JList lstProducts = new JList(prod);
			lstProducts.setFont(new Font("Arial", Font.PLAIN, 14));
			JList lstPurchases = new JList(purch);
			lstPurchases.setFont(new Font("Arial", Font.PLAIN, 14));
			
			JTextField fieldID1 = new JTextField("   Enter the purchase ID here...");
			fieldID1.setFont(new Font("Arial", Font.ITALIC, 14));
			JTextField fieldID2 = new JTextField("   Enter the purchase ID here...");
			fieldID2.setFont(new Font("Arial", Font.ITALIC, 14));
			
			JTextField newProduct = new JTextField("   Enter the new product ID here...");
			newProduct.setFont(new Font("Arial", Font.ITALIC, 14));
			JTextField fieldCustomer = new JTextField("   Enter the customer ID here...");
			fieldCustomer.setFont(new Font("Arial", Font.ITALIC, 14));
			
			JTextField fieldProduct = new JTextField("   Enter the product ID here...");
			fieldProduct.setFont(new Font("Arial", Font.ITALIC, 14));
			
			JButton searchPurchase = new JButton("Search for a purchase");
			searchPurchase.setFont(new Font("Arial", Font.BOLD, 16));
			JButton removePurchase = new JButton("Remove purchase");
			removePurchase.setFont(new Font("Arial", Font.BOLD, 16));
			JButton editPurchase = new JButton("Edit purchase");
			editPurchase.setFont(new Font("Arial", Font.BOLD, 16));
			JButton addPurchase = new JButton("Add a new purchase");
			addPurchase.setFont(new Font("Arial", Font.BOLD, 16));
			
			JLabel empty3 = new JLabel("");
			JButton exit = new JButton("Exit program");
			exit.setFont(new Font("Arial", Font.BOLD, 16));
			JLabel empty4 = new JLabel("");
			
			fr.setLayout(new GridLayout(6,3));  
			
			fr.add(empty1); fr.add(title); fr.add(empty2);
			fr.add(lstCustomers); fr.add(lstProducts); fr.add(lstPurchases);
			fr.add(fieldCustomer); fr.add(fieldProduct); fr.add(addPurchase);
			fr.add(fieldID1); fr.add(searchPurchase); fr.add(removePurchase);
			fr.add(fieldID2); fr.add(newProduct); fr.add(editPurchase);
			fr.add(empty3); fr.add(exit); fr.add(empty4);
			
			fr.setSize(800, 600);    
			fr.setVisible(true);   
			
			addPurchase.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) {
					  int customerID, productID;
					  try {
					        customerID = Integer.parseInt(fieldCustomer.getText());
					        productID = Integer.parseInt(fieldProduct.getText());
					        manager.addPurchase(customerID, productID);
					        JOptionPane.showMessageDialog(new JFrame(), manager.getStatusMessage(),
					        		"Query Result", JOptionPane.INFORMATION_MESSAGE);
					    } catch (NumberFormatException ex) {
					    	  JOptionPane.showMessageDialog(new JFrame(),"Invalid data format entered!\n"
					    			  + "(" + ex.getLocalizedMessage() + ")\nPlease retry!", "Error",
					    		        JOptionPane.ERROR_MESSAGE);
					    }
				  } 
			});
			
			editPurchase.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) {
					  int purchaseID, productID;
					  try {
						  purchaseID = Integer.parseInt(fieldID2.getText());
						  productID = Integer.parseInt(newProduct.getText());
						  manager.editPurchase(purchaseID, purchaseID);
					        JOptionPane.showMessageDialog(new JFrame(), manager.getStatusMessage(),
					        		"Query Result", JOptionPane.INFORMATION_MESSAGE);
					  }
					  catch (NumberFormatException ex) {
						  JOptionPane.showMessageDialog(new JFrame(),"Invalid data format entered!\n"
			    			  + "(" + ex.getLocalizedMessage() + ")\nPlease retry!", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
					  }
				  }
			});
			
			searchPurchase.addActionListener(new ActionListener() { 
				 public void actionPerformed(ActionEvent e) {
					  int purchaseID;
					  try {
						  purchaseID = Integer.parseInt(fieldID1.getText());
						  Purchase p = manager.findPurchase(purchaseID);
						  if(p != null) {
						        JOptionPane.showMessageDialog(new JFrame(), "Here's the found purchase:\n" + p,
						        		"Purchase found!", JOptionPane.INFORMATION_MESSAGE);
						  }
						  else {
							  JOptionPane.showMessageDialog(new JFrame(), "Sorry, no purchase with ID = " + purchaseID + " was found!",
						        		"Purchase found!", JOptionPane.ERROR);
						  }
					  }
					  catch (NumberFormatException ex) {
						  JOptionPane.showMessageDialog(new JFrame(),"Invalid data format entered!\n"
			    			  + "(" + ex.getLocalizedMessage() + ")\nPlease retry!", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
					  }
				  } 
			});
			
			removePurchase.addActionListener(new ActionListener() { 
				 public void actionPerformed(ActionEvent e) {
					  int purchaseID;
					  try {
						  purchaseID = Integer.parseInt(fieldID1.getText());
						  manager.removePurchase(purchaseID);
						  JOptionPane.showMessageDialog(new JFrame(), manager.getStatusMessage(),
					        		"Query Result", JOptionPane.INFORMATION_MESSAGE); 
					  }
					  catch (NumberFormatException ex) {
						  JOptionPane.showMessageDialog(new JFrame(),"Invalid data format entered!\n"
			    			  + "(" + ex.getLocalizedMessage() + ")\nPlease retry!", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
					  }
				  } 
			});
			
			exit.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) {
					  int dialogResult = JOptionPane.showConfirmDialog (null, 
							  "Are you sure you want to exit?", "Exit", 1);
					  if(dialogResult == JOptionPane.YES_OPTION){
						  fr.dispose();
						  System.exit(0);
					  }
				  } 
			});
		}
	}
		
		
	public static void main(String[] args) {    
		new Main();    
	}
}