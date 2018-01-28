import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WriteReviews")
public class WriteReviews extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = null;
		Helper helper = new Helper(request,out);
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		
		
		
		
		
		
		
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		String type = request.getParameter("type");
		String Name = request.getParameter("name");
		String maker = request.getParameter("maker");
		double price = Double.parseDouble(request.getParameter("price"));
		
	
		
		String productOnSale = "Yes";
		
		String manufacturerRebate = "No";
		String emailId ="abi";
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		String reviewDate = dateFormat.format(today);
		//Date reviewDate = dateFormat.parse(reviewDateeee);
		
			
				
			out.println("<div id=\"body\"><article class=\"expanded\"><h3 align=\"center\">Write a Review</h3>");
			out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			out.println("<form action=\"WriteReviews\" method=\"POST\">");
			
			out.println("<input type='hidden' name='productType' value='"+type+"'>");
			out.println("<input type='hidden' name='productName' value='"+Name+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='manufacturer' value='"+maker+"'>");
			//out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");

			out.println("<input type='hidden' name='productOnSale' value='"+productOnSale+"'>");
			out.println("<input type='hidden' name='manufacturerRebate' value='"+manufacturerRebate+"'>");
			out.println("<input type='hidden' name='emailId' value='"+emailId+"'>");
			out.println("<input type='hidden' name='reviewDate' value='"+reviewDate+"'>");
			
			
			out.println("<p><label><b>Product Category: </b></label>"+type+"</p>");
			out.println("<p><label><b>Product Name: </b></label>"+Name+"</p>");
			out.println("<p><label><b>Product Price: </b></label>"+price+"</p>");
			//out.println("<p><label><b>Retailer Name: </b></label>"+retailer+"</p>");
			
			out.println("<p><label><b>Retailer Name: </b></label><input name=\"retailer\" type=\"text\"></p>");
			out.println("<p><label><b>Retailer Zip: </b></label><input name=\"retailerZip\" type=\"text\"></p>");
			out.println("<p><label><b>Retailer City: </b></label><input name=\"retailerCity\"type=\"text\" /></p>");
			out.println("<p><label><b>Retailer State: </b></label><input name=\"retailerState\"type=\"text\" /></p>");
			
			out.println("<p><label><b>Product On Sale: </b></label>"+productOnSale+"</p>");
			out.println("<p><label><b>Manufacturer Name: </b></label>"+maker+"</p>");
			out.println("<p><label>Manufacturer Rebate:</label>"+manufacturerRebate+"</p>");
			
			out.println("<p><label><b>User Id: </b></label>"+emailId+"</p>");
			
			out.println("<p><label><b>User Age: </b></label><input name=\"userAge\" type=\"text\"></p>");
			out.println("<p><label><b>User Gender: </b></label><input name=\"userGender\"type=\"text\" /></p>");
			out.println("<p><label><b>User Occupation: </b></label><input name=\"userOccupation\"type=\"text\" /></p>");
			
			out.println("<p><label><b>Review Rating: </b></label><select name='reviewRating'><option name='reviewRating' value='1' selected>1</option><option name='reviewRating' value='2'>2</option>");
			out.println("<option name='reviewRating' value='3'>3</option><option name='reviewRating' value='4'>4 </option><option name='reviewRating' value='5'>5</option></select></p>");
			
			out.println("<p><label><b>Review Date: </b></label>"+reviewDate+"</p>");
			out.println("<p><label><b>Review Text: </b></label><textarea rows=\"4\" cols=\"50\" name=\"reviewText\"></textarea>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\"  class=\"formbutton\" value=\"Submit Review\" type=\"submit\" /></p>");
			
			out.println("</form></div></fieldset></article><div class=\"clear\"></div></div>");
		
		//printSideBar(out);
		
		out.close();
	

}

 public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	
	      
	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = null;
		Helper helper = new Helper(request,out);
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");	  
		  
		  
	response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		
		String type = request.getParameter("type");
		String Name = request.getParameter("name");
		String maker = request.getParameter("maker");
		double price = Double.parseDouble(request.getParameter("price"));
		
	
		
		String productOnSale = "Yes";
		
		String manufacturerRebate = "No";
		String emailId ="abi";
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		String reviewDate = dateFormat.format(today);
        
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		String productName = request.getParameter("productName");
		String productType_1 = request.getParameter("productType");
		String retailer = request.getParameter("retailer");
		String retailerZip = request.getParameter("retailerZip");
		String retailerCity_1 = request.getParameter("retailerCity");
		String retailerState = request.getParameter("retailerState");
		String productOnSale_1 = request.getParameter("productOnSale");
		String manufacturer = request.getParameter("manufacturer");
		String manufacturerRebate_1 = request.getParameter("manufacturerRebate");
		String emailId_1 = request.getParameter("emailId");
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		String userGender = request.getParameter("userGender");
		String userOccupation = request.getParameter("userOccupation");
		int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
		String productType		= request.getParameter("productType");
		
		DateFormat dateFormat_1 = new SimpleDateFormat("MM/dd/yyyy");
		Date reviewDate_1 = null;
		try{
			reviewDate_1 = dateFormat_1.parse(request.getParameter("reviewDate"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		String reviewText = request.getParameter("reviewText");
		
		 //if(userAge != 0 && userGender != null && userGender.length() != 0 && userOccupation != null && userOccupation.length() != 0
			//	&& reviewText != null && reviewText.length() != 0
				//&& retailerZip != null && retailerZip.length() != 0 && retailerCity_1 != null && retailerCity_1.length() != 0
			//	&& retailerState != null && retailerState.length() != 0)
		
				
			//MongoDBDataStoreUtilities.insertReview(productName, productType_1, price, retailer, retailerZip,retailerCity_1,retailerState,
													//productOnSale_1, manufacturer, manufacturerRebate_1, emailId_1, userAge, userGender,
													//userOccupation, reviewRating, reviewDate, reviewText);
													
			pw.println("<p>Review has been submitted ! View your review in the products review section");
												
		
		//else
		//{
			//pw.println("<p>Fields may be empty. Please go back and fill all the fields of the review form");
//		}			
	
	
}
}



