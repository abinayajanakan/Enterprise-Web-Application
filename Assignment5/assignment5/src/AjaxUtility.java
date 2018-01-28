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

public class AjaxUtility {
	
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
				
				if(type.equals("Accessoryone"))
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
	
	
	public static void main(String args[]){
		
		//AjaxUtility.getProductsFromMySQL();
		
	}
	
}


