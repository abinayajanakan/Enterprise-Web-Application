import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CreateCustomer extends HttpServlet {
	
	Customer customer;
	HashMap<String,Customer> customers;
	String firstName;
	String lastName;
    String emailId;
	String phoneNumber;
	String password;
	String rePassword;
	
	PopulateCustomersHashmap pch;
	
	public void init()
	{
		//pch = new PopulateCustomersHashmap();
		//customers = new HashMap<String, Customer>();
		customers = MySqlDataStoreUtilities.getCustomers();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		//System.out.println("Inside SalesmanPortal's DoGet ");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		emailId = request.getParameter("emailId");
		phoneNumber = request.getParameter("phoneNumber");
		password = request.getParameter("password");
		rePassword = request.getParameter("rePassword");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Best Deal</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2><h3>Salesman Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"start selected\"><a href=\"SalesmanPortalServlet\">Customer List</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=createCustomer\">Create Customer</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=addOrder\">Add Order</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=updateOrder\">Update Order</a></li>");
		out.println("<li><a href=\"SalesmanPortalServlet?type=deleteOrder\">Delete Order</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		
		
		if(firstName != null && firstName.length() != 0) {
            firstName = firstName.trim();
        }
		if(lastName != null && lastName.length() != 0) {
				lastName = lastName.trim();
			}
		if(emailId != null && emailId.length() != 0) {
				emailId = emailId.trim();
			}
		if(phoneNumber != null && phoneNumber.length() != 0) {
				phoneNumber = phoneNumber.trim();
			}
		if(password != null && password.length() != 0) {
				password = password.trim();
		}
		
		
		if(password.equals(rePassword) && firstName != "" && lastName != "" && emailId != "" && phoneNumber != "" && password != "" && rePassword != "")
		{
			customers = MySqlDataStoreUtilities.getCustomers();
			boolean isExist = false;
			for(Map.Entry<String,Customer> m :customers.entrySet())
			{
				Customer c = m.getValue();
				
				if(c.getemailId().equals(emailId))
				{
					isExist = true;
				}
			}
			
			if(isExist)
			{
				out.println("<div id=\"body\"><article><h3 align=\"center\">Customer Creation Failed</h3>");
				out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
				out.println("<p>Email Id already exists</p>");
				out.println("<p>Please go back and try again !</p>");
			}
			else
			{
				MySqlDataStoreUtilities.insertCustomer(firstName, lastName, emailId, password, phoneNumber);
				
				out.println("<div id=\"body\"><article><h3 align=\"center\">Customer Created</h3>");
				out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
				out.println("<h3>Customer created and added to Customer List<h3>");
			}
			
			//customer = new Customer(firstName, lastName, emailId, password, phoneNumber);
			//customers = pch.getCustomerHashMap();
			//customers = MySqlDataStoreUtilities.getCustomers();
			//System.out.println("customers hashmap:"+customers);
			//customers.put(customer.getemailId(), customer);
			//pch.writeCustomerHashMap(customers);
			//System.out.println("customers after update: ");
			//pch.getCustomerHashMap();
		}
	
		else
		{
			out.println("<h3>The following error may have occured:<h3>");
			out.println("<p>1: Any one field may have been kept empty</p>");
			out.println("<p>2: Passwords do not match</p>");
			out.println("<p>Please go back and try again !</p>");
		}
	
		out.println("</div></fieldset></article</div></div></body></html>");
		out.close();

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
			
			
}


