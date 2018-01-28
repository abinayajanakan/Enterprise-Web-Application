import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesmanPortalServlet extends HttpServlet {
	
	HashMap<String,Customer> customers;
	String firstName;
	String lastName;
    String emailId;
	String phoneNumber;
	String password;
	
	Order order;
	OrderDataStore ods;
	HashMap<String,Order> orders;
	
	PopulateCustomersHashmap pch;
	
	public void init()
	{
		pch = new PopulateCustomersHashmap();
		//customers = new HashMap<String, Customer>();
		customers = MySqlDataStoreUtilities.getCustomers();
		
		ods = new OrderDataStore();
		orders = new HashMap<String, Order>();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		//System.out.println("Inside SalesmanPortal's DoGet ");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		
		String type = request.getParameter("type");
		System.out.println("type: "+type);
		
		//String attr="";
		//attr = (String)request.getAttribute("type");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>smart portable</h2><h3>Salesman Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"start selected\"><a href=\"SalesmanPortalServlet\">Customer List</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=createCustomer\">Create Customer</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=orderList\">Order List</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=addOrder\">Add Order</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=updateOrder\">Update Order</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
	
		
		if(type == null)
		{
			//customers = pch.getCustomerHashMap();
			customers = MySqlDataStoreUtilities.getCustomers();
			System.out.println("Customers in Customer List in SalesmanPortalServlet: "+customers);
			
			String Ordernum="",productname="",orderDate="",shipping="",delivery;
			
			out.println("<div id=\"body\"><article><h3 align=\"center\">Customer List</h3>");
			out.println("<fieldset><div style=\"width:800; margin-right:auto; margin-left:auto;\">");
			out.println("<table>");
			out.println("<tr><td>Name</td><td>Email Id</td><td>Phone Number</td><td>Password</td></tr>");
				
				for(Map.Entry<String,Customer> m :customers.entrySet()){
					System.out.println(m.getKey());
					Customer c = m.getValue();
					
					firstName = c.getfirstName();
					lastName = c.getlastName();
					emailId = c.getemailId();
					phoneNumber = c.getPhoneNumber();
					password = c.getpassword();
				
					out.println("<td>"+firstName+" "+lastName+"</td>");
					out.println("<td>"+emailId+"</td>");
					out.println("<td>"+phoneNumber+"</td>");
					out.println("<td>"+password+"</td></tr>");
				
				}
			
			out.println("</table>");
			out.println("</div></fieldset></article</div></div></body></html>");
		}
		
		else if(type.equals("createCustomer"))
		{	
			out.println("<div id=\"body\"><article><h3 align=\"center\">Create a new Customer</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"/assignment4/CreateCustomer\" method=\"get\">");
			out.println("<p><label for=\"name\">First Name:</label><input name=\"firstName\" id=\"firstName\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label for=\"email\">Last Name:</label><input name=\"lastName\" id=\"lastName\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Phone Number:</label><input name=\"phoneNumber\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Email Id:</label><input name=\"emailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Password:</label><input name=\"password\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Re Enter Password:</label><input name=\"rePassword\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Create Customer\" type=\"submit\" /></p>");
			out.println("</form></div></fieldset></article</div></div></body></html>");
		}
		
		else if(type.equals("orderList"))
		{	
			out.println("<div id=\"body\"><article><h3 align=\"center\">Display Customer Orders</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("<form action=\"SalesmanPortalServlet\" method=\"post\">");
			out.println("<input type='hidden' name='queryType' value='displayOrders'>");
			out.println("<p><label>Customer Email Id:</label><input name=\"customerEmailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Display Customer Orders\" type=\"submit\" /></p>");
			
			out.println("</form></div></fieldset></article</div></div></body></html>");
		}
		
		else if(type.equals("updateOrder"))
		{	
			out.println("<div id=\"body\"><article><h3 align=\"center\">Update Order</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			out.println("<form action=\"SalesmanPortalServlet\" method=\"post\">");
			out.println("<input type='hidden' name='queryType' value='updateThisOrder'>");
			out.println("<p><label>Order Id:</label><input name=\"customerOrder\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Display Order To Update\" type=\"submit\" /></p>");
			
			out.println("</form></div></fieldset></article</div></div></body></html>");
		}
		
		
		else if(type.equals("addOrder"))
		{	
			Random r = new Random();
			int Low = 1;
			int High = 572431;
			int R = r.nextInt(High-Low) + Low;
			String orderId = "B#"+R;
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date today = new Date();
			String orderDate = dateFormat.format(today).toString();
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 14);
			
			Date date = cal.getTime();
			String DATE_FORMAT = "MM/dd/yyyy"; 
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
			String deliveryDate = sdf.format(date);
	
	
			out.println("<div id=\"body\"><article><h3 align=\"center\">Add Order</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"SalesmanPortalServlet\" method=\"post\">");
			out.println("<input type='hidden' name='queryType' value='addOrder'>");
			out.println("<input type='hidden' name='orderId' value='"+orderId+"'>");
			out.println("<input type='hidden' name='orderDate' value='"+orderDate+"'>");
			out.println("<input type='hidden' name='deliveryDate' value='"+deliveryDate+"'>");
			
			out.println("<p><label>Customer Email Id:</label><input name=\"customerEmailId\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Delivery Address:</label><input name=\"deliveryAddress\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Item Name:</label><input name=\"itemName\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Item Price:</label><input name=\"itemPrice\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Item Qty:</label><input name=\"itemQty\" id=\"email\" value=\"\" type=\"text\" /></p>");
			out.println("<p><label>Order Date: </label>"+orderDate+"</p>");
			out.println("<p><label>Delivery Date: </label>"+deliveryDate+"</p>");
			out.println("<p><label>Order Id:</label>"+orderId+"</p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Add Order\" type=\"submit\" /></p>");
			out.println("</form></div></fieldset></article</div></div></body></html>");
		}
			
		out.close();

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String queryType="";
		queryType = request.getParameter("queryType");
		
        
		
		if(queryType==null)
		{
			doGet(request, response);
		}
		
		if(queryType.equals("addOrder"))
		{
			out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
			out.println("<body><div id=\"container\">");
			out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>smart portable</h2><h3>Salesman Portal</h3></header>");
			out.println("<nav><ul>");
			out.println("<li class=\"start selected\"><a href=\"SalesmanPortalServlet\">Customer List</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=createCustomer\">Create Customer</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=addOrder\">Add Order</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=updateOrder\">Update Order</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=orderList\">Order List</a></li>");
			out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
			
			String customerEmailId = request.getParameter("customerEmailId");
			String deliveryAddress = request.getParameter("deliveryAddress");
			String itemName = request.getParameter("itemName");
			Float itemPrice = Float.parseFloat(request.getParameter("itemPrice"));
			Integer itemQty = Integer.parseInt(request.getParameter("itemQty"));
			String orderId = request.getParameter("orderId");
			String orderDate = request.getParameter("orderDate");
			String deliveryDate = request.getParameter("deliveryDate");
			
			MySqlDataStoreUtilities.insertOrderItem(itemName, orderId, itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress);
			
			out.println("<h3><br><br>Order No "+orderId+" for Customer "+customerEmailId+" has been Placed Succesfully.</h3><br><br>");
		}
		
		if(queryType.equals("displayOrders"))
		{
			out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
			out.println("<body><div id=\"container\">");
			out.println("<header><h1><a href=\"/\">smart<span></span></a></h1><h2>Buy smart</h2><h3>Salesman Portal</h3></header>");
			out.println("<nav><ul>");
			out.println("<li class=\"start selected\"><a href=\"SalesmanPortalServlet\">Customer List</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=createCustomer\">Create Customer</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=addOrder\">Add Order</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=updateOrder\">Update Order</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=orderList\">Order List</a></li>");
			out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
			
			String thisCustomer = request.getParameter("customerEmailId");
			boolean isCustomerExist = false;
			
			HashMap<String,OrderItem> orderItems;
			orderItems = MySqlDataStoreUtilities.getOrderItems();
			
			String customerEmailId;
			String orderId;
			String itemName;
			float itemPrice;
			int itemQty;
			String orderDate;
			String deliveryDate;
			String deliveryAddress;
	
			//orders = ods.getOrderHashMap();
	
			out.println("<div id=\"body\"><article><h3 align=\"center\">Order List</h3>");
			out.println("<fieldset><div style=\"width:800; margin-right:auto; margin-left:auto;\">");
			
			out.println("<table>");
			out.println("<tr><td>Email Id</td><td>Order Id</td><td>Item Name</td><td>Item Price</td><td>Item Qty</td><td>Order Date</td><td>Delivery Date</td><td>Shipping Address</td></tr>");
				
			for(Map.Entry<String,OrderItem> m :orderItems.entrySet()){
					
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

					if(thisCustomer.equals(customerEmailId))
					{
						//isCustomerExist = true;
						out.println("<form action='CancelOrderServlet' method = 'post'>");
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
					
					
			
		}
			out.println("</table>");	
			
			out.println("</div></fieldset></article></div></div></body></html>");
		}
		
		if(queryType.equals("updateThisOrder"))
		{
			out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
			out.println("<body><div id=\"container\">");
			out.println("<header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Buy smart</h2><h3>Salesman Portal</h3></header>");
			out.println("<nav><ul>");
			out.println("<li class=\"start selected\"><a href=\"SalesmanPortalServlet\">Customer List</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=createCustomer\">Create Customer</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=addOrder\">Add Order</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=updateOrder\">Update Order</a></li>");
			out.println("<li><a href=\"SalesmanPortalServlet?type=orderList\">Order List</a></li>");
			out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
			
			String thisOrder = request.getParameter("customerOrder");
			
			HashMap<String,OrderItem> orderItems;
			orderItems = MySqlDataStoreUtilities.getOrderItems();
			
			String customerEmailId;
			String orderId;
			String itemName;
			float itemPrice;
			int itemQty;
			String orderDate;
			String deliveryDate;
			String deliveryAddress;
			
			out.println("<div id=\"body\"><article><h3 align=\"center\">Update Order</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			
			for(Map.Entry<String,OrderItem> m :orderItems.entrySet()){
					
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

					if(thisOrder.equals(orderId))
					{
						
						out.println("<form action=\"UpdateOrderBySalesman\" method=\"post\">");
						out.println("<input type='hidden' name='queryType' value='updateOrder'>");
						out.println("<input type='hidden' name='orderId' value='"+orderId+"'>");
						out.println("<input type='hidden' name='orderDate' value='"+orderDate+"'>");
						
						out.println("<p><label>Customer Email Id: </label><input name=\"customerEmailId\" id=\"email\" value='"+customerEmailId+"' type=\"text\" /></p>");
						out.println("<p><label>Delivery Address: </label><input name=\"deliveryAddress\" id=\"email\" value='"+deliveryAddress+"' type=\"text\" /></p>");
						out.println("<p><label>Item Name: </label><input name=\"itemName\" id=\"email\" value='"+itemName+"' type=\"text\" /></p>");
						out.println("<p><label>Item Price: </label><input name=\"itemPrice\" id=\"email\" value='"+itemPrice+"' type=\"text\" /></p>");
						out.println("<p><label>Item Qty: </label><input name=\"itemQty\" id=\"email\" value='"+itemQty+"' type=\"text\" /></p>");
						out.println("<p><label>Order Date: </label>"+orderDate+"</p>");
						out.println("<p><label>Delivery Date: </label><input name=\"deliveryDate\" id=\"email\" value='"+deliveryDate+"' type=\"text\" /></p>");
						out.println("<p><label>Order Id: </label>"+orderId+"</p>");
						out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Order\" type=\"submit\" /></p></form>");
					}
	
			}
	
			out.println("</div></fieldset></article</div></div></body></html>");
		}

	}
			
			
}


