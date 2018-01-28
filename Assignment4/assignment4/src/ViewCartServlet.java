import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class ViewCartServlet extends HttpServlet {
	
	
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
        
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
			out.println("<li><a href=\"ViewCartServlet\">Cart(0)</a></li>");
		}
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
		
		//---------------------------------------------------------------------------------------------------------------------------------
		
		Cart thisCart;
		thisCart = (Cart) session.getAttribute("cart");
		
		if(firstName != null && !firstName.isEmpty())
		{
			if(thisCart==null)
			{
				out.println("<h1>Cart is Empty</h1>");
			}
			else
			{
				 HashMap<String, List<Object>> items = thisCart.getCartItems();
			
					if(items.isEmpty())
					{
						
						out.println("<h1>Cart is Empty </h1>");
						out.println("<tr>");
						out.println("<td>");
						out.println("</td>");
						out.println("</tr>");
						
					}
					else
					{
						out.println("<h1>Items Currently in  cart </h1>");
						out.println("<hr>");
						out.println("<table  frame='box' rules='rows'>");
						out.println("<tr><td></td><td>Product</td><td>Price&nbsp&nbsp&nbsp&nbsp</td><td>Quantity</td><td></td>");
						
						String key="";
						//for(HashMap.Entry<String, List<Object>> entry : items.entrySet())
						{
						//	key = entry.getKey();
							//System.out.println("Key: "+key);
							//List<Object> values = entry.getValue();
							//String quantityKey = values.get(1);
							out.println("<form action='RemoveItemServlet'><input type='hidden' name='name' value='"+key+"'>");
							
							//out.println("<tr><td><img src ='"+values.get(3)+"' width = '100' height = '100'></td><td>"+values.get(0)+"  </td><td>"+"$"+values.get(1)+"</td>");
							out.println("<td><select name='"+key+"'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
							out.println("<td><input type='submit' name='action1' value='RemoveItem'></td></tr></form>");
						}
						out.println("<form action='RemoveItemServlet'>");
						out.println("<tr ><td align='center' colspan='5'><input type='submit' name='action1' value='CheckOut'></td></tr></form>");
						out.println("</table>");
					}		
		}
		}
		else
		{
			out.println("<p>Please login to add items in your cart !");
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
		out.println("<p>&copy; smart portable 2017. by Abinaya janakan</p></div></footer></div>");
		out.println("</body></html>");
		
		out.println("</body>");
		out.println("</html>");
	}

}