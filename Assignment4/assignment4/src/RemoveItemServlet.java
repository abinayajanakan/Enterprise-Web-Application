import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class RemoveItemServlet extends HttpServlet {
	
	
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String firstName = (String)session.getAttribute("firstName");
        
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\"><header><h1><a href=\"/\">smart<span>Deal</span></a></h1><h2>Buy smart</h2>");
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
		
		//--------------------------------------------------------------------------------------------------------------------------
		
		String action = request.getParameter("action1");
			
			
		if(action.equals("RemoveItem"))
		{
			System.out.println("Inside remove's if");
			String name = request.getParameter("name");
			
			Cart ekart;
			ekart = (Cart) session.getAttribute("cart");
			
			ekart.deleteFromCart(name);
			session.setAttribute("cart", ekart);
			ekart = (Cart) session.getAttribute("cart");
			RequestDispatcher rd = request.getRequestDispatcher("ViewCartServlet");
			rd.forward(request,response);
		}
		
		if(action.equals("CheckOut"))
		{	
			RequestDispatcher rd = request.getRequestDispatcher("CheckoutServlet");
			rd.forward(request,response);
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