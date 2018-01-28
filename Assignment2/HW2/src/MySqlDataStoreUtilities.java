import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class MySqlDataStoreUtilities {
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exdatabase", "root", "1234");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void insertCustomer(String username, String password, String usertype)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			String insertIntoCustomerRegisterQuery = "INSERT INTO registration(username,password,usertype) " + "VALUES (?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			
			pst.setString(1,username);
			pst.setString(2,password);
			pst.setString(3,usertype);
			
			
			
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,User> getCustomers()
	{
		User user = null;
		HashMap<String,User> users = new HashMap<String, User>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exdatabase?autoReconnect=true&useSSL=false", "root", "root");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT username, password, usertype FROM registration");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String username = rs.getString("username");
				String password = rs.getString ("password");
				String usertype = rs.getString ("usertype");
				
			   
				user = new User(username, password, usertype);
				users.put(username, user);
			}
			//System.out.println("Customers: "+customers);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return users;
	}
	
	public static HashMap<String,String> getAdminCredentials()
	{
		HashMap<String,String> admins = new HashMap<String, String>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase?autoReconnect=true&useSSL=false", "root", "root");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT emailAddress, password FROM admin_login_details");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String emailAddress = rs.getString("emailAddress");
				String password = rs.getString ("password");
			   
				admins.put(emailAddress, password);
			}
			//System.out.println("Customers: "+customers);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return admins;
	}
	
	private static void insertCustomersFromSerializedFile()
	{
		//PopulateCustomersHashmap pch;
		//Customer customer = null;
		//HashMap<String,Customer> customers = new HashMap<String, Customer>();
		//pch = new PopulateCustomersHashmap();
		
		//customers = pch.getCustomerHashMap();
		//System.out.println("Customers: "+customers);
		//for(Map.Entry<String,Customer> m :customers.entrySet())
		{
			//Customer c = m.getValue();
			//System.out.println("Customer: "+c);
			//insertCustomer(c.getfirstName(), c.getlastName(), c.getemailId(), c.getpassword(), c.getPhoneNumber());
			
		}
		
	}
	
	public static void insertOrderItem(String fname,String phoneNumber,String emailId,String orderId,String orderDate,String deliverydate,String shippingAddress)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			String insertIntoOrderItemQuery = "INSERT INTO order_item(fname, phoneNumber, emailId, orderId, orderDate, deliverydate, shippingAddress) " + "VALUES (?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
			pst.setString(1, fname);
			pst.setString(2,phoneNumber);
			pst.setString(3,emailId);
			pst.setString(4,orderId);
			pst.setString(5,orderDate);
			pst.setString(6,deliverydate);
			
			pst.setString(7,shippingAddress);
			
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertOrderTotal(String orderId, String orderDate, String deliveryDate, float totalAmount, String customerEmailId, String deliveryAddress)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase?autoReconnect=true&useSSL=false", "root", "root");
			
			String insertIntoOrderTotalQuery = "INSERT INTO order_total(orderId, orderDate, deliveryDate, totalAmount, customerEmailId, deliveryAddress) " + "VALUES (?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,orderId);
			pst.setString(2,orderDate);
			pst.setString(3,deliveryDate);
			pst.setFloat(4,totalAmount);
			pst.setString(5,customerEmailId);
			pst.setString(6,deliveryAddress);
			
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static HashMap<String,OrderItem> getOrderItems()
	{
		OrderItem orderItem = null;
		HashMap<String,OrderItem> orderItems = new HashMap<String, OrderItem>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase?autoReconnect=true&useSSL=false", "root", "root");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT orderId, itemType, itemName, itemId, itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress FROM order_item");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String orderId = rs.getString("orderId");
				String itemType = rs.getString ("itemType");
				Integer itemId = rs.getInt ("itemId");
				String itemName = rs.getString ("itemName");
				Float itemPrice = rs.getFloat ("itemPrice");
				Integer itemQty = rs.getInt ("itemQty");
				String orderDate = rs.getString("orderDate");
				String deliveryDate = rs.getString ("deliveryDate");
				String customerEmailId = rs.getString ("customerEmailId");
				String deliveryAddress = rs.getString ("deliveryAddress");
			   
				//orderItem = new OrderItem(orderId, itemType, itemName, itemId, itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress);
				orderItems.put(customerEmailId+orderId+itemName, orderItem);
			}
			//System.out.println("OrderItems: "+orderItems);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return orderItems;
	}
	
	public static void deleteOrderItem(String orderId, String itemName, String customerEmailId)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase?autoReconnect=true&useSSL=false", "root", "root");
			
			String insertIntoOrderTotalQuery = "DELETE FROM order_item where orderId=? and itemName=?";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,orderId);
			pst.setString(2,itemName);
			//pst.setString(3,deliveryDate);
		
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static LinkedHashMap<String, ArrayList<Object>> getTop5SoldProducts()
	{
		LinkedHashMap<String, ArrayList<Object>> top5SoldProducts = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase?autoReconnect=true&useSSL=false", "root", "root");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT itemName, itemPrice, SUM(itemQty) as sumItemQty FROM order_item GROUP BY itemName order by sumItemQty desc limit 5;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String itemName = rs.getString("itemName");
				Float itemPrice = rs.getFloat ("itemPrice");
				Integer sumItemQty = rs.getInt ("sumItemQty");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(itemName);
				itemArray.add(itemPrice);
				itemArray.add(sumItemQty);
				
				top5SoldProducts.put(itemName, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return top5SoldProducts;
		
	}
	
	
	public static void updateOrderItem(String itemName, String orderId, float itemPrice, int itemQty, String orderDate, String deliveryDate, String customerEmailId, String deliveryAddress)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase?autoReconnect=true&useSSL=false", "root", "root");
			
			String insertIntoOrderItemQuery = "Update order_item set itemName=?, itemPrice=?, itemQty=?, deliveryDate=?, deliveryAddress=? where orderId=?";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
			pst.setString(1,itemName);
			pst.setFloat(2,itemPrice);
			pst.setInt(3,itemQty);
			pst.setString(4,deliveryDate);
			pst.setString(5,deliveryAddress);
			pst.setString(6,orderId);
			
			
			pst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		
		//HashMap<String,Customer> customers = new HashMap<String, Customer>();
		//customers = MySqlDataStoreUtilities.getCustomers();
		//System.out.println("Customers: "+customers);
		
		//MySqlDataStoreUtilities.getOrderItems();
		//MySqlDataStoreUtilities.deleteOrderItem("B#226329", "iPad Air 1", "adil@gmail.com");
		//MySqlDataStoreUtilities.updateOrderItem();
		
	}
	
}


