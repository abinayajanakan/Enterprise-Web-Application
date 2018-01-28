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
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ViewReviews extends HttpServlet {
	

	
	public void init() throws ServletException{

		
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String fname=(String)session.getAttribute("firstName");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		out.println("<body onload='init()'><div id=\"container\"><header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>buy smart</h2>");
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
	
		
		String userid=(String)session.getAttribute("userid");
		
		String productName = request.getParameter("productName");
		
		HashMap<String,Review> reviews;
		//reviews = MongoDBDataStoreUtilities.getReviews();
		
		HashMap<String,Review> thisProductReviews;
		thisProductReviews = new HashMap<String, Review>();
		
		
		//For mysql code
		//for(Map.Entry<String,Review> m :reviews.entrySet())
		{
			//
//String key = m.getKey();
			//Review c = m.getValue();
			
			//if(c.getProductName().equals(productName))
			{
				//thisProductReviews.put(key, c);
			}
			
		}
		
		System.out.println("thisProductReviews hashmap in ViewReviews: "+thisProductReviews);
		
		if(thisProductReviews.isEmpty()){
			out.println("<h3>There are no Reviews for this product<h3>");
		}
		else	
		{
			String name;
			String emailId;
			int reviewRating;
			Date date;
			String reviewText;
			
			String retailer;
			String retailerZip;
			String retailerCity;
			
			out.println("<h3 align='center'>Product Reviews</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td>Product Name</td><td>Email Id</td><td>Review Rating</td><td>Review Date</td><td>Retailer</td><td>Retailer Zip</td><td>Retailer City</td><td>Review text</td></tr>");
				
			int no=1;
			
				for(Map.Entry<String,Review> m :thisProductReviews.entrySet()){
					
					Review c = m.getValue();
					
					name = c.getProductName();
					emailId = c.getEmailId();
					reviewRating = c.getReviewRating();
					
					retailer = c.getRetailer();
					retailerZip = c.getRetailerZip();
					retailerCity = c.getRetailerCity();
					
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					date = c.getReviewDate();
					String reviewDate = dateFormat.format(date);
					
					reviewText = c.getReviewText();
					
					out.println("<tr><td>"+no+"</td>");
					out.println("<td>"+name+"</td>");
					out.println("<td>"+emailId+"</td>");
					out.println("<td>"+reviewRating+"</td>");
					out.println("<td>"+reviewDate+"</td>");
					out.println("<td>"+retailer+"</td>");
					out.println("<td>"+retailerZip+"</td>");
					out.println("<td>"+retailerCity+"</td>");
					out.println("<td>"+reviewText+"</td></tr>");
					
					no++;
					
				}
			
			out.println("</table>");	
		}
 
	
}
}