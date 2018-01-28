import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Set;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DataAnalyticsServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
     
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String productType = "";
		//productType = request.getParameter("productType");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"DataAnalyticsServlet\">Data Analytics</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div align='center' id=\"body\"><article><h3 align=\"center\">Select any one query from the folowing and click Execute Query</h3>");
		out.println("<fieldset>");

		out.println("<form action='DataAnalyticsServlet' method='post'>");
		out.println("<p><select name='queryType'>");
		out.println("<option name='queryType' value='1' selected>Average product prices</option>");
		out.println("<option name='queryType' value='2'>Total price of products</option>");
		out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
		out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
		out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
		
		out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
		out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
		out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
		out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
		out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
		
		out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
		out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
		out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
		out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
		out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
		
		out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
		out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
		out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
		out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
		out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
		
		out.println("</select></p>");
		out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
		out.println("</form>");
		
		
		out.println("</fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String queryType = "";
		queryType = request.getParameter("queryType");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"DataAnalyticsServlet\">Data Analytics</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div align='center' id=\"body\"><article><h3 align=\"center\">Select any one query from the folowing and click Execute Query</h3>");
		out.println("<fieldset>");

		if(queryType.equals("1"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1' selected>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			request.getRequestDispatcher("new.html").forward(request, response);
			
			
			out.println("</table>");
		}
		
		if(queryType.equals("2"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1' selected>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			request.getRequestDispatcher("new1.html").forward(request, response);
			
			
			out.println("</table>");
		}
		
		
		
		
		
		out.println("</fieldset></article</div></div></body></html>");
		
		out.close();
	}
			
}


