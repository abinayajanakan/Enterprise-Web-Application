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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase", "root", "1234");
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
		HashMap<String, Tablet> tablets= new HashMap<String, Tablet>();
		HashMap<String, Television> televisions= new HashMap<String, Television>();
		HashMap<String, Accessory> accessories= new HashMap<String, Accessory>();
		
		HashMap<String, Product> productsMap= new HashMap<String, Product>();
		
		Smartphone smartphone;
		Laptop laptop;
		Tablet tablet;
		Television television;
		Accessory accessory; 
		Product product;
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM products");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				Integer id1 = rs.getInt("id");
				String id = id1.toString();
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

				if(type.equals("Tablet"))
				{
					tablet = new Tablet();
					
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
					accessory = new Accessory();
					
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
		HashMap<String, Tablet> tablets;
		HashMap<String, Television> televisions;
		HashMap<String, Accessory> accessories;
		
		SAXParserForProducts sp = new SAXParserForProducts();
		products = sp.loadDataStore();
		
		smartphones = (HashMap<String,Smartphone>)products.get(0);
		tablets = (HashMap<String, Tablet>)products.get(1);
		laptops = (HashMap<String, Laptop>)products.get(2);
		televisions = (HashMap<String, Television>)products.get(3);
		accessories = (HashMap<String, Accessory>)products.get(4);
		
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			
			for(Map.Entry<String,Smartphone> m :smartphones.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
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
			
			
			for(Map.Entry<String,Tablet> m :tablets.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Tablet";
				Tablet s = m.getValue();
				
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
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
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
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
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
			
			for(Map.Entry<String,Accessory> m :accessories.entrySet())
			{
				String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertProducts);
				
				String type = "Accessory";
				Accessory s = m.getValue();
				
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
	
	public static void deleteAllProductsFromTableMySQL()
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			String safeUpdates = "SET SQL_SAFE_UPDATES=0; ;";
			Statement stmt1 = conn.createStatement();
			stmt1.execute(safeUpdates);
			
			String deleteFromProducts = "delete from products;";
			Statement stmt = conn.createStatement();
			stmt.execute(deleteFromProducts);
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void insertProductInMySQL(String id, String productType, String retailer, String imagePath, String productName,
												String company, String condition, Float price, String color)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			String insertProducts = "INSERT INTO products(id, type, retailer, image, name, company, price, color) " + "VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertProducts);
			
			pst.setString(1,id);
			pst.setString(2,productType);
			pst.setString(3,retailer);
			pst.setString(4,imagePath);
			pst.setString(5,productName);
			pst.setString(6,company);
			pst.setFloat(7,price);
			pst.setString(8,color);
			//pst.setString(9,condition);
			
			pst.execute();
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void deleteProductFromMySQL(String productName)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			String insertIntoOrderTotalQuery = "DELETE FROM products where name=?;";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,productName);
			
			pst.execute();
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void updateProductFromMySQL(String id, String productType, String retailer, String imagePath, String productName,
												String company, String condition, Float price, String color)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			String insertIntoOrderItemQuery = "Update products set retailer=?, image=?, name=?, company=?, price=?, color=? where id=?";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
			pst.setString(1,retailer);
			pst.setString(2,imagePath);
			pst.setString(3,productName);
			pst.setString(4,company);
			pst.setFloat(5,price);
			pst.setString(6,color);
			
			pst.setString(7,id);
			
			pst.execute();
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static void insertCustomer(String firstName, String lastName, String emailId, String password, String phoneNumber)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			String insertIntoCustomerRegisterQuery = "INSERT INTO registration(firstName,lastName,emailId, password, phoneNumber) " + "VALUES (?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(3,emailId);
			pst.setString(4,password);
			pst.setString(5,phoneNumber);
			
			pst.execute();
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,Customer> getCustomers()
	{
		Customer customer = null;
		HashMap<String,Customer> customers = new HashMap<String, Customer>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT firstName, lastName, emailId, password, phoneNumber FROM registration");
			ResultSet rs = s.getResultSet();
			
			while (rs.next ())
			{
				String firstName = rs.getString("firstName");
				String lastName = rs.getString ("lastName");
				String emailId = rs.getString ("emailId");
				String password = rs.getString ("password");
				String phoneNumber = rs.getString ("phoneNumber");
			   
				customer = new Customer(firstName, lastName, emailId, password, phoneNumber);
				customers.put(emailId, customer);
			}
			//System.out.println("Customers: "+customers);
			rs.close ();
			s.close ();
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return customers;
	}
	
	public static HashMap<String,String> getAdminCredentials()
	{
		HashMap<String,String> admins = new HashMap<String, String>();
		
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
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
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return admins;
	}
	
	private static void insertCustomersFromSerializedFile()
	{
		PopulateCustomersHashmap pch;
		Customer customer = null;
		HashMap<String,Customer> customers = new HashMap<String, Customer>();
		pch = new PopulateCustomersHashmap();
		
		customers = pch.getCustomerHashMap();
		//System.out.println("Customers: "+customers);
		for(Map.Entry<String,Customer> m :customers.entrySet())
		{
			Customer c = m.getValue();
			//System.out.println("Customer: "+c);
			insertCustomer(c.getfirstName(), c.getlastName(), c.getemailId(), c.getpassword(), c.getPhoneNumber());
			
		}
		
	}
	
	public static void insertOrderItem(String itemName, String orderId, float itemPrice, int itemQty, String orderDate, String deliveryDate, String customerEmailId, String deliveryAddress)
	{
		try
		{
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "root", "1234");
			
			String insertIntoOrderItemQuery = "INSERT INTO order_item(orderId,itemName,itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress) " + "VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderItemQuery);
			
			pst.setString(1,orderId);
			pst.setString(2,itemName);
			pst.setFloat(3,itemPrice);
			pst.setInt(4,itemQty);
			pst.setString(5,orderDate);
			pst.setString(6,deliveryDate);
			pst.setString(7,customerEmailId);
			pst.setString(8,deliveryAddress);
			
			pst.execute();
			
			conn.close();
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Smartdatabase?autoReconnect=true&useSSL=false", "1234", "root");
			
			String insertIntoOrderTotalQuery = "INSERT INTO order_total(orderId, orderDate, deliveryDate, totalAmount, customerEmailId, deliveryAddress) " + "VALUES (?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(insertIntoOrderTotalQuery);
			
			pst.setString(1,orderId);
			pst.setString(2,orderDate);
			pst.setString(3,deliveryDate);
			pst.setFloat(4,totalAmount);
			pst.setString(5,customerEmailId);
			pst.setString(6,deliveryAddress);
			
			pst.execute();
			
			conn.close();
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
			   
				orderItem = new OrderItem(orderId, itemType, itemName, itemId, itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress);
				orderItems.put(customerEmailId+orderId+itemName, orderItem);
			}
			//System.out.println("OrderItems: "+orderItems);
			rs.close ();
			s.close ();
			
			conn.close();
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
			
			conn.close();
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
			
			conn.close();
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
			
			conn.close();
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
		
		//MySqlDataStoreUtilities.getProductsFromMySQL();
		
	}
	
}


