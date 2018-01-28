import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class AddOrder extends HttpServlet {
	
	Order order;
	OrderDataStore ods;
	HashMap<String,Order> orders;
	
	public void init()
	{
		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}
	
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">Smart portable<span>Deal</span></a></h1><h2>Buy smart</h2><h3>Salesman Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"start selected\"><a href=\"SalesmanPortalServlet\">Customer List</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=createCustomer\">Create Customer</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=addOrder\">Add Order</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=updateOrder\">Update Order</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=orderList\">Order List</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
        
		//------------------------------------------------------------------------------------------------------------------------------
		
		String shippingAddress = request.getParameter("shippingAddress");
		String emailId = request.getParameter("emailId");
		float finalAmount = Float.parseFloat(request.getParameter("amount"));
		String items = request.getParameter("items");
		
		Random r = new Random();
		int Low = 1;
		int High = 572431;
		int R = r.nextInt(High-Low) + Low;
		String orderId = "B#"+R;
		
		int C = r.nextInt(High-Low);
		String confirmationNo = "C#"+R;
		
		//Static production of the final amount in values. Change it !!!!
		int NewLow = 400;
		int NewHigh = 3500;
		//float finalAmount = r.nextInt(NewHigh-NewLow) + NewLow;
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = new Date();
		
		String orderDate = dateFormat.format(today).toString();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 14);
		Date date = cal.getTime();
		String DATE_FORMAT = "MM/dd/yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
		String deliverydate = sdf.format(date);
		
		/*
		Cart ekart;
        ekart = (Cart) session.getAttribute("cart");
		
		HashMap<String, List<Object>> items = ekart.getCartItems();
		String userid=(String)session.getAttribute("userid");
		
		String orderInfo;
		Float totalAmount=ekart.getTotalAmount();
		ArrayList<String> orderItems = new ArrayList<String>();
		
		
		for(HashMap.Entry<String, List<Object>> entry : items.entrySet()){
			
			String key = entry.getKey();
			List<Object> values = entry.getValue();	
			
			orderInfo = (String)values.get(0);
			System.out.println("Item: "+orderInfo);
			orderItems.add(orderInfo);
			
			//order = new Order(orderId, confirmationNo, userid, orderDate, deliverydate, shippingAddress, (float)values.get(2), orderItems);
		}
		*/
		
		//System.out.println("Order Items: "+orderItems);
		
		//System.out.println("Shipping Address: "+shippingAddress);
		ArrayList<String> orderItems = new ArrayList<String>();
		order = new Order(orderId, confirmationNo, emailId, orderDate, deliverydate, shippingAddress, finalAmount, orderItems);
		//System.out.println("Shipping Address: "+shippingAddress);
		//order.setItemsHashMap(items);
		
		//System.out.println("Items Hashmap: "+order.getItemsHashMap());
		//System.out.println("Order Items in Order Object: "+order.getOrderItems());
		//System.out.println("User Id in Order Object: "+order.getcustomerEmailId());
		
		orders = ods.getOrderHashMap();
		System.out.println("Orders: "+orders);
		orders.put(order.getorderId(), order);
		ods.writeOrderHashMap(orders);
		
		
		//session.removeAttribute("cart");
		out.println("<h3><br><br>Your Order No "+orderId+" has been Placed Succesfully. The order will be delivered by " + deliverydate + " </h3><br><br>");	
		
		//printSideBar(out);
		
		out.close();
	
}

public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
}


	public void printSideBar(PrintWriter out)
	{
		out.println("</article></section>");
		out.println("<aside class=\"sidebar\">");
		out.println("<ul><li><h4>SmartPhones</h4><ul>");
		out.println("<a href=\"index.html\">Apple</a></li><li><a href=\"examples.html\">Samsung</a></li>");
		out.println("<li><a href=\"#\">HTC</a></li><li><a href=\"#\">Motorola</a></li><li><a href=\"#\">LG</a></li></ul></li><li><h4>Tablets</h4><ul>");
		out.println("<li><a href=\"index.html\">Apple</a></li><li><a href=\"examples.html\">Samsung</a></li><li><a href=\"#\">Acer</a></li>");
		out.println("<li><a href=\"#\">Amazon</a></li><li><a href=\"#\">LG</a></li></ul></li><li><h4>Laptops</h4><ul>");
		out.println("<li><a href=\"index.html\">Apple</a></li><li><a href=\"examples.html\">Dell</a></li><li><a href=\"#\">HP</a></li><li><a href=\"#\">Lenovo</a></li>");
		out.println("<li><a href=\"#\">Microsoft</a></li></ul></li><li><h4>Tv's</h4><ul><li><a href=\"index.html\">LG</a></li><li><a href=\"examples.html\">Samsung</a></li>");
		out.println("<li><a href=\"#\">Vizio</a></li><li><a href=\"#\">Sony</a></li><li><a href=\"#\">Sharp</a></li>");
		out.println("</ul></li></ul></aside><div class=\"clear\"></div></div>");
		out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
		out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
		out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
		out.println("<p>&copy; BestDeal 2016. by Mohammed Shethwala</p></div></footer></div>");
		out.println("</body></html>");
		
		out.println("</body>");
		out.println("</html>");
	}

}