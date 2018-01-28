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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportabledatabase", "root", "1234");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	
	public static ArrayList<Object> getProductsFromMySQL()
	{
		ArrayList<Object> products = new ArrayList<Object>();
		
		HashMap<String, Smartphone> smartphones= new HashMap<String, Smartphone>();
		HashMap<String, Laptop> laptops= new HashMap<String, Laptop>();
		HashMap<String, Tabletone> tablets= new HashMap<String, Tabletone>();
		HashMap<String, Television> televisions= new HashMap<String, Television>();
		HashMap<String, Accessoryone> accessories= new HashMap<String, Accessoryone>();
		
		HashMap<String, Product> productsMap= new HashMap<String, Product>();
		
		Smartphone smartphone;
		Laptop laptop;
		Tabletone tablet;
		Television television;
		Accessoryone accessory; 
		Product product;
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportabledatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM product");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				Integer id1 = rs.getInt("id");
				String id = rs.toString();
				String type = rs.getString("type");
				String retailer = rs.getString ("retailer");
				String image = rs.getString ("image");
				String name = rs.getString ("name");
				String company = rs.getString ("company");
				Float price = rs.getFloat("price");
				String color = rs.getString ("color");
				
				product = new Product();
					
				product.setType(type);
				product.setId(id);
				product.setRetailer(retailer);
				product.setImage(image);
				product.setName(name);
				product.setCompany(company);
				product.setPrice(price);
				product.setColor(color);
					
				productsMap.put(name, product);
				
				if(type.equals("Smartphone"))
				{
					smartphone = new Smartphone();
					
					smartphone.setId(id);
					smartphone.setRetailer(retailer);
					smartphone.setImage(image);
					smartphone.setName(name);
					smartphone.setCompany(company);
					smartphone.setPrice(price);
					smartphone.setColor(color);
					
					smartphones.put(name, smartphone);
				}

				if(type.equals("Tabletone"))
				{
					tablet = new Tabletone();
					
					tablet.setId(id);
					tablet.setRetailer(retailer);
					tablet.setImage(image);
					tablet.setName(name);
					tablet.setCompany(company);
					tablet.setPrice(price);
					tablet.setColor(color);
					
					tablets.put(name, tablet);
				}	   
				
				if(type.equals("Laptop"))
				{
					laptop = new Laptop();
					
					laptop.setId(id);
					laptop.setRetailer(retailer);
					laptop.setImage(image);
					laptop.setName(name);
					laptop.setCompany(company);
					laptop.setPrice(price);
					laptop.setColor(color);
					
					laptops.put(name, laptop);
				}
				
				if(type.equals("Television"))
				{
					television = new Television();
					
					television.setId(id);
					television.setRetailer(retailer);
					television.setImage(image);
					television.setName(name);
					television.setCompany(company);
					television.setPrice(price);
					television.setColor(color);
					
					televisions.put(name, television);
				}
				
				if(type.equals("Accessory"))
				{
					accessory = new Accessoryone();
					
					accessory.setId(id);
					accessory.setRetailer(retailer);
					accessory.setImage(image);
					accessory.setName(name);
					accessory.setCompany(company);
					accessory.setPrice(price);
					accessory.setColor(color);
					
					accessories.put(name, accessory);
				}
				
			}
			
			products.add(smartphones);
			products.add(tablets);
			products.add(laptops);
			products.add(televisions);
			products.add(accessories);
			products.add(productsMap);
			
			//System.out.println("ProductsMap: "+productsMap);
			
			/*
			for(Map.Entry<String,Product> m :productsMap.entrySet())
			{
				Product p = m.getValue();
				
				System.out.println("Product: " + p.getName());
			}
			*/
			
			rs.close ();
			s.close ();
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return products;
	}
	
	public static void insertAllProductsToMySQLFromXML()
	{
		ArrayList<Object> products;
		HashMap<String, Smartphone> smartphones;
		HashMap<String, Laptop> laptops;
		HashMap<String, Tabletone> tablets;
		HashMap<String, Television> televisions;
		HashMap<String, Accessoryone> accessories;
		
		SAXParserForProducts sp = new SAXParserForProducts();
		products = sp.loadDataStore();
		
		smartphones = (HashMap<String,Smartphone>)products.get(0);
		tablets = (HashMap<String, Tabletone>)products.get(1);
		laptops = (HashMap<String, Laptop>)products.get(2);
		televisions = (HashMap<String, Television>)products.get(3);
		accessories = (HashMap<String, Accessoryone>)products.get(4);
		
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportabledatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			
			for(Map.Entry<String,Smartphone> m :smartphones.entrySet())
			{
				String insertProducts = "INSERT INTO product(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Smartphone";
				Smartphone s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				//pst.setString(9,s.getCondition());
				
				pst.execute();
			}
			
			
			for(Map.Entry<String,Tabletone> m :tablets.entrySet())
			{
				String insertProducts = "INSERT INTO product(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Tablet";
				Tabletone s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				//pst.setString(9,s.getCondition());
				
				pst.execute();
			}
			
			for(Map.Entry<String,Laptop> m :laptops.entrySet())
			{
				String insertProducts = "INSERT INTO product(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Laptop";
				Laptop s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				//pst.setString(9,s.getCondition());
				
				pst.execute();
			}
			
			for(Map.Entry<String,Television> m :televisions.entrySet())
			{
				String insertProducts = "INSERT INTO product(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Television";
				Television s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				//pst.setString(9,s.getCondition());
				
				pst.execute();
			}
			
			for(Map.Entry<String,Accessoryone> m :accessories.entrySet())
			{
				String insertProducts = "INSERT INTO product(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Accessoryone";
				Accessoryone s = m.getValue();
				
				pst.setString(1,s.getId());
				pst.setString(2,type);
				pst.setString(3,s.getRetailer());
				pst.setString(4,s.getImage());
				pst.setString(5,s.getName());
				pst.setString(6,s.getCompany());
				pst.setFloat(7,s.getPrice());
				pst.setString(8,s.getColor());
				//pst.setString(9,s.getCondition());
				
				pst.execute();
			}
			
			conn.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void insertCustomer(String username, String password, String usertype)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportabledatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportabledatabase?autoReconnect=true&useSSL=false", "root", "root");
			
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportabledealdatabase?autoReconnect=true&useSSL=false", "root", "root");
			
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
	
	public static LinkedHashMap<String, ArrayList<Object>> getSalesReport()
	{
		LinkedHashMap<String, ArrayList<Object>> SalesReport = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT prod_name, price,num_available FROM Sales_Report;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String prod_name = rs.getString("prod_name");
				Float price = rs.getFloat ("price");
				Integer num_available = rs.getInt ("num_available");
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				itemArray.add(prod_name);
				itemArray.add(price);
				itemArray.add(num_available);
				
				SalesReport.put(prod_name, itemArray);
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return SalesReport;
		
	}
	
	
	public static LinkedHashMap<String, ArrayList<Object>> getData()
	{
		LinkedHashMap<String, ArrayList<Object>> Data = new LinkedHashMap<String, ArrayList<Object>>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT productId, productName FROM product;");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String productId = rs.getString("productId");
				String productName = rs.getString("productName");
				
			   
				ArrayList<Object> itemArray = new ArrayList<Object>();
				
				itemArray.add(productId);
				itemArray.add(productName);
				
				
				Data.put(productId,itemArray );
			}
			//System.out.println("Top 5 sold items: "+top5SoldProducts);
			rs.close ();
			s.close ();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Data;
		
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


