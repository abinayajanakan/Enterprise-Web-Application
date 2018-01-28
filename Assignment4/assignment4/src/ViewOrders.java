import java.io.*;
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


public class ViewOrders extends HttpServlet {
	
	Order order;
	OrderDataStore ods;
	HashMap<String,Order> orders;
	
	public void init() throws ServletException{

		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fname=(String)session.getAttribute("firstName");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		out.println("<body onload='init()'><div id=\"container\"><header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>smart portable</h2>");
		out.println("<form  name='autofillform1' action=''>");
		out.println("<div name='autofillform'>");
		out.println("<strong>Search Products: </strong>");
		out.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'>");
		out.println("<div id='auto-row'>");
		out.println("<table border='0' id='complete-table' class='popupBox'></table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		
		out.println("</header>");
		
		if(fname != null && !fname.isEmpty())
		{
			System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(fname);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"LoggedInHomeServlet\">Home</a></li>");
		}
		else{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
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
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
		
//--------------------------------------------------------------------------------------------------------------------------------------------
	
	if(fname==null)
	{
		out.println("<h1>Please Login to View Orders!!!!!!!!!!!!!!!</h1>");		
	}	 
	else
	{
		String userid=(String)session.getAttribute("userid");
		
		HashMap<String,OrderItem> orderItems;
		orderItems = MySqlDataStoreUtilities.getOrderItems();
		
		HashMap<String,OrderItem> customerOrderItems;
		customerOrderItems = new HashMap<String, OrderItem>();
		
		//Serialized file order code:
		/*
		orders = ods.getOrderHashMap();
		HashMap<String,Order> customerOrders;
		customerOrders = new HashMap<String, Order>();
		
		for(Map.Entry<String,Order> m :orders.entrySet())
		{
			String key = m.getKey();
			Order c = m.getValue();
			
			if(c.getcustomerEmailId().equals(userid))
			{
				customerOrders.put(key, c);
			}
			
		}
		*/
		
		//For mysql code
		for(Map.Entry<String,OrderItem> m :orderItems.entrySet())
		{
			String key = m.getKey();
			OrderItem c = m.getValue();
			
			if(c.getCustomerEmailId().equals(userid))
			{
				customerOrderItems.put(key, c);
			}
			
		}
		
		
		if(customerOrderItems.isEmpty()){
			out.println("<h3>There are no Orders for this User Id<h3>");
		}
		else	
		{
			String customerEmailId;
			String orderId;
			String itemName;
			float itemPrice;
			int itemQty;
			String orderDate;
			String deliveryDate;
			String deliveryAddress;
			
			
			out.println("<h3>Your Orders</h3>");
			out.println("<table>");
			out.println("<tr><td>Email Id</td><td>Order Id</td><td>Item Name</td><td>Item Price</td><td>Item Qty</td><td>Order Date</td><td>Delivery Date</td><td>Shipping Address</td></tr>");
				
				for(Map.Entry<String,OrderItem> m :customerOrderItems.entrySet()){
					
					//System.out.println(m.getKey());
					OrderItem c = m.getValue();
					
					customerEmailId = c.getCustomerEmailId();
					orderId = c.getOrderId();
					itemName = c.getItemName();
					itemPrice = c.getItemPrice();
					itemQty = c.getItemQty();
					orderDate = c.getOrderDate();
					deliveryDate = c.getDeliveryDate();
					deliveryAddress = c.getDeliveryAddress();

					out.println("<form action='CancelOrderServlet'>");
					out.println("<input type='hidden' name='userid' value='"+customerEmailId+"'>");
					out.println("<input type='hidden' name='ordernum' value='"+orderId+"'>");
					out.println("<input type='hidden' name='delivery' value='"+deliveryDate+"'>");
					out.println("<input type='hidden' name='itemName' value='"+itemName+"'>");
					
					out.println("<td>"+customerEmailId+"</td>");
					out.println("<td>"+orderId+"</td>");
					out.println("<td>"+itemName+"</td>");
					out.println("<td>"+itemPrice+"</td>");
					out.println("<td>"+itemQty+"</td>");
					out.println("<td>"+orderDate+"</td>");
					out.println("<td>"+deliveryDate+"</td>");
					out.println("<td>"+deliveryAddress+"</td>");
					
					out.println("<td><input type='submit'  value='Cancel Order'></td></tr>");
					out.println("</form>");	
				
				}
			
			out.println("</table>");	
		}
 
	}
}
}