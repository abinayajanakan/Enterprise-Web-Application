import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class ConfirmationServlet extends HttpServlet {
	
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
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
        
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Best Deal</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		out.println("<body onload='init()'><div id=\"container\"><header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2>");
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
		
		if(firstName != null && !firstName.isEmpty())
		{
			System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(firstName);
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
		
		if(firstName != null && !firstName.isEmpty())
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
		
//------------------------------------------------------------------------------------------------------------------------------------------------
		
		String shippingAddress = request.getParameter("shippingAddress");
		String fname = request.getParameter("firstName");
		String phoneNumber = request.getParameter("phoneNumber");
		String emailId = request.getParameter("emailId");
		String cardHolderName = request.getParameter("cardHolderName");
		String ccNumber = request.getParameter("ccNumber");
		String expDate = request.getParameter("expDate");
		String cvv = request.getParameter("cvv");
		
		//Float finalAmount = request.getParameter("finalAmount");
		//float finalAmount = Float.parseFloat(request.getParameter("finalAmount"));
		
		Random r = new Random();
		int Low = 1;
		int High = 572431;
		int R = r.nextInt(High-Low) + Low;
		String orderId = "B#"+R;
		
		int C = r.nextInt(High-Low);
		String confirmationNo = "C#"+R;
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = new Date();
		String orderDate = dateFormat.format(today).toString();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 14);
		Date date = cal.getTime();
		String DATE_FORMAT = "MM/dd/yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
		String deliverydate = sdf.format(date);
		
		Cart ekart;
        ekart = (Cart) session.getAttribute("cart");
		
		HashMap<String, List<Object>> items = ekart.getCartItems();
		String userid=(String)session.getAttribute("userid");
		
		Float totalAmount=ekart.getTotalAmount();
		
		ArrayList<String> orderItems = new ArrayList<String>();
		String orderInfo;
		
		String itemName;
		float itemPrice;
		int itemQty;
		
		for(Map.Entry<String, List<Object>> entry : items.entrySet()){
			
			String key = entry.getKey();
			List<Object> values = entry.getValue();	
			
			itemName = (String)values.get(0);
			itemPrice = (Float)values.get(1);
			itemQty = (Integer)values.get(2);
			
			//To insert every item in MySql table order_item
			MySqlDataStoreUtilities.insertOrderItem(itemName, orderId, itemPrice, itemQty, orderDate, deliverydate, userid, shippingAddress);
			
			
			orderInfo = (String)values.get(0);
			//System.out.println("Item: "+orderInfo);
			orderItems.add(orderInfo);
		}
		
		//This code is to insert order in MySQL Table
		//MySqlDataStoreUtilities.insertOrderItem(itemName, orderId, itemPrice, itemQty, orderDate, deliverydate);
		MySqlDataStoreUtilities.insertOrderTotal(orderId, orderDate, deliverydate, totalAmount,userid, shippingAddress);
		//Code for MySql ends here
		
		
		//This is the code to insert order with serialized method 
		System.out.println("Order Items: "+orderItems);
		System.out.println("Shipping Address: "+shippingAddress);
		order = new Order(orderId, confirmationNo, userid, orderDate, deliverydate, shippingAddress, totalAmount, orderItems);
		System.out.println("Shipping Address: "+shippingAddress);
		
		//order.setItemsHashMap(items);
		
		//System.out.println("Items Hashmap: "+order.getItemsHashMap());
		System.out.println("Order Items in Order Object: "+order.getOrderItems());
		System.out.println("User Id in Order Object: "+order.getcustomerEmailId());
		
		orders = ods.getOrderHashMap();
		System.out.println("Orders: "+orders);
		
		orders.put(order.getorderId(), order);
		ods.writeOrderHashMap(orders);
		//Code for serialized order ends here
		
	
		
		session.removeAttribute("cart");
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