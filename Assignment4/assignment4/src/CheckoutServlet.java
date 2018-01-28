import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Map;



public class CheckoutServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
        
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		out.println("<body onload='init()'><div id=\"container\"><header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>Best Price Guaranteed</h2>");
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
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		String action = request.getParameter("action1");
			
		if(action.equals("CheckOut"))
		{
			Cart thisCart;
			thisCart = (Cart) session.getAttribute("cart");
			HashMap<String, List<Object>> items = thisCart.getCartItems();
			
			for(Map.Entry<String, List<Object>> entry : items.entrySet()){
				String key = entry.getKey();
				List<Object> values = entry.getValue();
				//int quantity = Integer.parseInt(request.getParameter(key));
				int quantity = 1;
				thisCart.addToCart(key,(String)values.get(0),(float)values.get(1),quantity,(String)values.get(3));
			}
					
			int quantity=0;
			float price=0;
			float amount=0;
			
			for(Map.Entry<String, List<Object>> entry : items.entrySet()){
				String key = entry.getKey();
				List<Object> values = entry.getValue();
				price=(float)values.get(1);
				quantity=(int)values.get(2);
				amount=amount + price*quantity;
			}
			
			thisCart.setTotalAmount(amount);
				
			out.println("<div id=\"body\"><article class=\"expanded\"><h3 align=\"center\">Personal Information</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"ConfirmationServlet\" method=\"POST\"><p><label>First Name:</label><input name=\"firstName\" type=\"text\"></p>");
			out.println("<p><label>Last Name:</label><input name=\"lastName\"type=\"text\" /></p>");
			out.println("<p><label>Phone Number:</label><input name=\"phoneNumber\"type=\"text\" /></p><p><label>Email Id:</label><input name=\"emailId\"type=\"text\"></p>");
			out.println("<p><label>Shipping Address:</label><textarea rows=\"4\" cols=\"50\" name=\"shippingAddress\"></textarea>");
			out.println("</div></fieldset><fieldset></fieldset>");
			out.println("<h3 align=\"center\">Payment Information</h3><fieldset>");
			out.println("<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<p><label>Card Holder Name::</label><input name=\"cardHolderName\" type=\"text\"></p>");
			out.println("<p><label>Credit Card Number:</label>");
			out.println("<input name=\"ccNumber\"type=\"text\" /></p>");
			out.println("<p><label>Expriy Date:</label>");
			out.println("<input name=\"expDate\"type=\"text\" /></p>");
			out.println("<p><label>Cvv:</label>");
			out.println("<input name=\"cvv\"type=\"password\" /></p>");
			out.println("<p><label name='finalAmount' value='amount'>Total Amount: <b>"+amount+"</b></label>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Confirm Payment\" type=\"submit\" /></p>");
			out.println("</form></div></fieldset></article><div class=\"clear\"></div></div>");
		}
		//printSideBar(out);
		
		out.close();
	
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
		out.println("<p>&copy; Smart portable 2017. by Abinaya Janakan</p></div></footer></div>");
		out.println("</body></html>");
		
		out.println("</body>");
		out.println("</html>");
	}

}