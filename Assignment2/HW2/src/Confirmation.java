import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Confirmation")
public class Confirmation extends HttpServlet {
	
	
	
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	  response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		Helper helper = new Helper(request,out);
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
      
		
		
		
        HttpSession session = request.getSession();
		
		
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
		out.println("<h3><br><br>Your Order No "+orderId+" has been Placed Succesfully. The order will be delivered by " + deliverydate + " </h3><br><br>");	
			out.print("</div></div></div>");		
		helper.printHtml("site_footer.html");
		out.close();
		
		
		MySqlDataStoreUtilities.insertOrderItem(fname, phoneNumber, emailId, orderId, orderDate, deliverydate, shippingAddress);
	
}

public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
}


			
		

}