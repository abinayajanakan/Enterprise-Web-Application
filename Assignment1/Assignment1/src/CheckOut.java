import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String amountdue = request.getParameter("totalamount");
		Helper helper = new Helper(request,pw);
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		//pw.print("<a style='font-size: 24px;'>"+name+" Consoles</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		//int i = 1; int size= hm.size();
		//for(Map.Entry<String, Console> entry : hm.entrySet()){
			//Console console = entry.getValue();
			//if(i%3==1) pw.print("<tr>");
			//pw.print("<td><div id='shop_item'>");
			//pw.print("<h3>"+console.getName()+"</h3>");
			pw.print("<input type='text' name='CardNumber' value='' class='input'></input></br></br>");
			pw.print("<input type='text' name='ExpiryDate' value='' class='input'></input>");
			pw.print("<input type='submit' class='btnbuy' value='Buy Now' href='#'>");
			// pw.print("<li id='item'><img src='images/laptops/"+console.getImage()+"' alt='' /></li>");
			pw.print("<ul><li><form method='get' action='Cart'>" +
					//"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					//"<input type='hidden' name='maker' value='"+amountdue+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now' href='#'></input></form></li>");
			pw.print("<li><p>"+"Please enter your card details to pay the amount of $"+amountdue+"</li></p>");
			//pw.print("<li><a class='btnbuy' href='AccessoryList?maker="+CategoryName+"&console="+entry.getKey().toString()+"'>View Accessories</a></li>");
			//pw.print("<li><a class='btnreview' href='Review?name="+entry.getKey()+"&type=consoles&maker="+CategoryName+"&access='>Reviews</a></li>");
			pw.print("</ul></div></td>");
			// if(i%3==0 || i == size) 
			pw.print("</tr>");
			//i++;
		//}		
		pw.print("</table></div></div></div>");		
		helper.printHtml("site_footer.html");
		
	}
}
