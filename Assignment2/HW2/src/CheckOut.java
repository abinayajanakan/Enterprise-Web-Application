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
		
			
			
			
			
			// try
			pw.println("<div id=\"body\"><article class=\"expanded\"><h3 align=\"center\">Personal Information</h3>");
			pw.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			pw.println("<form action=\"Confirmation\" method=\"POST\"><p><label>First Name:</label><input name=\"firstName\" type=\"text\"></p>");
			pw.println("<p><label>Last Name:</label><input name=\"lastName\"type=\"text\" /></p>");
			pw.println("<p><label>Phone Number:</label><input name=\"phoneNumber\"type=\"text\" /></p><p><label>Email Id:</label><input name=\"emailId\"type=\"text\"></p>");
			pw.println("<p><label>Shipping Address:</label><textarea rows=\"4\" cols=\"50\" name=\"shippingAddress\"></textarea>");
			pw.println("</div></fieldset><fieldset></fieldset>");
			pw.println("<h3 align=\"center\">Payment Information</h3><fieldset>");
			pw.println("<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
			pw.println("<p><label>Card Holder Name::</label><input name=\"cardHolderName\" type=\"text\"></p>");
			pw.println("<p><label>Credit Card Number:</label>");
			pw.println("<input name=\"ccNumber\"type=\"text\" /></p>");
			pw.println("<p><label>Expriy Date:</label>");
			pw.println("<input name=\"expDate\"type=\"text\" /></p>");
			pw.println("<p><label>Cvv:</label>");
			pw.println("<input name=\"cvv\"type=\"password\" /></p>");
			pw.println("<p><label name='finalAmount' value='amount'>Total Amount: <b>"+amountdue+"</b></label>");
			pw.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Confirm Payment\" type=\"submit\" /></p>");
			pw.println("</form></div></fieldset></article><div class=\"clear\"></div></div>");
			
			
			
		//}		
		pw.print("</table></div></div></div>");		
		helper.printHtml("site_footer.html");
		
	}
}
