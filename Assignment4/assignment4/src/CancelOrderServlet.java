import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;
import java.util.Set;
import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;
import java.util.Iterator;


public class CancelOrderServlet extends HttpServlet {
	
	Order order;
	OrderDataStore ods;
	HashMap<String,Order> orders;
	
	public void init() throws ServletException{

		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}
	
	//doGet method is called from CancelOrder in Customers's ViewOrder page/servlet
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fname=(String)session.getAttribute("firstName");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Smart portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">Smart<span>portables</span></a></h1><h2>Buy Smart </h2>");
		out.println("</header>");
		
		if(fname != null && !fname.isEmpty())
		{
			//System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(fname);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"LoggedInHomeServlet\">Home</a></li>");
		}
		else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"Home.html\">Home</a></li>");
		}
		
		
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Smartphones\">SmartPhones</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Tablets\">Tablets</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Laptops\">Laptops</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Televisions\">Televisions</a></li>");
		out.println("<li><a href=\"#\">Accessories</a></li>");
		
		if(fname != null && !fname.isEmpty())
		{
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
			out.println("<li><a href=\"ViewOrders\">Your Orders</a></li>");
			out.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			out.println("<li><a href=\"LoginServlet\">Login</a></li>");
			out.println("<li><a href=\"SignUp.html\">SignUp</a></li>");
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
		}
		//out.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
	
		//----------------------------------------------------------------------------------------------------------------------------------------
		
		String userid = request.getParameter("userid");
		String itemName= request.getParameter("itemName");
		String ordernum= request.getParameter("ordernum");
		String delivery= request.getParameter("delivery");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 5);
		//Date date = cal.getTime();
		Date date = new Date();
		String DATE_FORMAT = "MM/dd/yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
		String checkdate = sdf.format(date);
		
		try {
			Date date1 = sdf.parse(delivery);
			Date date2 = sdf.parse(checkdate);
			long diff = date1.getTime() - date2.getTime();
			long daysDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			//System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			
			if(daysDiff>5)
			{
				HashMap<String,OrderItem> orderItems;
				orderItems = MySqlDataStoreUtilities.getOrderItems();
				
				//orders = ods.getOrderHashMap();
				
				for(Map.Entry<String,OrderItem> m :orderItems.entrySet())
				{
				  String key = m.getKey();
				  OrderItem c = m.getValue();
				  
				  String orderId = c.getOrderId();
				  String item = c.getItemName();
				  String emailid = c.getCustomerEmailId();
				  
				  //System.out.println ("orderID: " + orderId);
				  //System.out.println ("Outside if of CancelOrderServlet: " + orderId+item+emailid + ":::"+ordernum+itemName+userid);
				  if(orderId.equals(ordernum) && item.equals(itemName) && emailid.equals(userid))
					{
						//System.out.println ("Inside if of CancelOrderServlet: " + orderId+item+emailid + ":::"+ordernum+itemName+userid);
						MySqlDataStoreUtilities.deleteOrderItem(orderId, item, emailid);
					}
				}
				
				
				//System.out.println("Orders: "+orders);
				
				//ods.writeOrderHashMap(orders);
				
				
				out.println("<h3><br><br>The order has been cancelled from your order list<br><br><h3>");
				
				//RequestDispatcher view = request.getRequestDispatcher("ViewOrders");
				//view.forward(request, response);
			}
			else{
				out.println("<h3><br><br>The order has been shipped for delivery. The order cannot be cancelled!!<br><br><h3>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//doPost method is called by CancelOrder in SalesmanPortalServlet !!!
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fname=(String)session.getAttribute("firstName");
		
		String userid = request.getParameter("userid");
		String itemName= request.getParameter("itemName");
		String ordernum= request.getParameter("ordernum");
		String delivery= request.getParameter("delivery");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 5);
		//Date date = cal.getTime();
		Date date = new Date();
		String DATE_FORMAT = "MM/dd/yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
		String checkdate = sdf.format(date);
		
		try {
			Date date1 = sdf.parse(delivery);
			Date date2 = sdf.parse(checkdate);
			long diff = date1.getTime() - date2.getTime();
			long daysDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			//System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			
			if(daysDiff>5)
			{
				HashMap<String,OrderItem> orderItems;
				orderItems = MySqlDataStoreUtilities.getOrderItems();
				
				//orders = ods.getOrderHashMap();
				
				for(Map.Entry<String,OrderItem> m :orderItems.entrySet())
				{
				  String key = m.getKey();
				  OrderItem c = m.getValue();
				  
				  String orderId = c.getOrderId();
				  String item = c.getItemName();
				  String emailid = c.getCustomerEmailId();
				  
				  //System.out.println ("orderID: " + orderId);
				  //System.out.println ("Outside if of CancelOrderServlet: " + orderId+item+emailid + ":::"+ordernum+itemName+userid);
				  if(orderId.equals(ordernum) && item.equals(itemName) && emailid.equals(userid))
					{
						//System.out.println ("Inside if of CancelOrderServlet: " + orderId+item+emailid + ":::"+ordernum+itemName+userid);
						MySqlDataStoreUtilities.deleteOrderItem(orderId, item, emailid);
					}
				}
				
				
				//System.out.println("Orders: "+orders);
				
				//ods.writeOrderHashMap(orders);
				
				
				out.println("<h3><br><br>The order has been cancelled from the order list<br><br><h3>");
				
				//RequestDispatcher view = request.getRequestDispatcher("ViewOrders");
				//view.forward(request, response);
			}
			else{
				out.println("<h3><br><br>The order has been shipped for delivery. The order cannot be cacncelled!!<br><br><h3>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.close();
	}
}