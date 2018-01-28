import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateOrderBySalesman extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String queryType="";
		queryType = request.getParameter("queryType");
		
        out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>buy smart</h2><h3>Salesman Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"start selected\"><a href=\"SalesmanPortalServlet\">Customer List</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=createCustomer\">Create Customer</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=addOrder\">Add Order</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=updateOrder\">Update Order</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=orderList\">Order List</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		if(queryType.equals("updateOrder"))
		{
			String customerEmailId = request.getParameter("customerEmailId");
			String deliveryAddress = request.getParameter("deliveryAddress");
			String itemName = request.getParameter("itemName");
			Float itemPrice = Float.parseFloat(request.getParameter("itemPrice"));
			Integer itemQty = Integer.parseInt(request.getParameter("itemQty"));
			String orderId = request.getParameter("orderId");
			String orderDate = request.getParameter("orderDate");
			String deliveryDate = request.getParameter("deliveryDate");
			
			MySqlDataStoreUtilities.updateOrderItem(itemName, orderId, itemPrice, itemQty, orderDate, deliveryDate, customerEmailId, deliveryAddress);
			
			out.println("<h3><br><br>Order No "+orderId+" for Customer "+customerEmailId+" has been updated succesfully.</h3><br><br>");
		}
		
		
	}
			
			
}


