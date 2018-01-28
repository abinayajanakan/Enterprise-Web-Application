import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class LoginServlet extends HttpServlet
{
	Customer customer;
	HashMap<String,Customer> customers;
	HashMap<String,String> admins;
    String emailId;
	String password;
	
	PopulateCustomersHashmap pch;
	
	public void init()
	{
		pch = new PopulateCustomersHashmap();
		//customers = new HashMap<String, Customer>();
		customers = MySqlDataStoreUtilities.getCustomers();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
	out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
	out.println("</head><body><div id=\"container\">");
	out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>Buy smart</h2></header>");
	out.println("<h3 align=\"center\">Customer Login</h3>");
	out.println("<fieldset>");
	out.println("<div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
	out.println("<form action=\"/assignment4/LoginServlet\" method=\"POST\">");
	out.println("<p><label>Email Id:</label>");
	out.println("<input name=\"emailId\" id=\"name\" value=\"\" type=\"text\" /></p>");
	out.println("<p><label>Password:</label>");
	out.println("<input name=\"password\" id=\"email\" value=\"\" type=\"password\" /></p>");
	out.println("<p><input type='radio' name='usertype' value='customer' checked='checked'>&nbspCustomer&nbsp&nbsp&nbsp <input type='radio' name='usertype' value='storemanager'>&nbspStore Manager&nbsp&nbsp&nbsp <input type='radio' name='usertype' value='salesmanager'>&nbspSales Manager </p>");
	out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Login\" type=\"submit\" /></p>");
	out.println("</form></div></fieldset></div></body></html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("doPost Start: ");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(true);
		
		emailId = request.getParameter("emailId");
		password = request.getParameter("password");
		String customertype = request.getParameter("usertype");
		
		//checking if textboxes are empty or not
		
		if(emailId != null && emailId.length() != 0) {
				emailId = emailId.trim();
			}
		if(password != null && password.length() != 0) {
				password = password.trim();
			}
		
		//customers = pch.getCustomerHashMap();
		//System.out.println("Customers: "+customers);
		
		if(customertype.equals("storemanager"))
		{
			admins = MySqlDataStoreUtilities.getAdminCredentials();
			boolean success = false;
	
			for(Map.Entry<String,String> m :admins.entrySet())
			{
				String id = m.getKey();
				String pwrd = m.getValue();
				
				if(emailId.equals(id) && password.equals(pwrd))
				{
					success = true;
					RequestDispatcher rd = request.getRequestDispatcher("StoreManagerPortal");
					rd.forward(request,response);
				}
				
			}
			
			if(success==false)
			{
				out.println("<b>Invalid Username or Password. Please try again !<b>");
			}
			
			
		}
		
		else if(customertype.equals("salesmanager"))
		{
			admins = MySqlDataStoreUtilities.getAdminCredentials();
			boolean success = false;
			
			for(Map.Entry<String,String> m :admins.entrySet())
			{
				String id = m.getKey();
				String pwrd = m.getValue();
				
				if(emailId.equals(id) && password.equals(pwrd))
				{
					success = true;
					RequestDispatcher rd = request.getRequestDispatcher("SalesmanPortalServlet");
					rd.forward(request,response);
				}
			}
			
			if(success==false)
			{
				out.println("<b>Invalid Username or Password. Please try again !<b>");
			}
		}
	
		else if(customertype.equals("customer"))
		{
			customers = MySqlDataStoreUtilities.getCustomers();
			boolean success = false;
			
			for(Map.Entry<String,Customer> m :customers.entrySet())
			{
				Customer c = m.getValue();
				
				if(c.getemailId().equals(emailId) && c.getpassword().equals(password))
				{
					success = true;
					
					System.out.println("Email Id:"+c.getemailId());
					System.out.println("Password:"+c.getpassword());
					
					session.setAttribute("firstName",c.getfirstName());
					session.setAttribute("userid",emailId);
					
					RequestDispatcher view = request.getRequestDispatcher("LoggedInHomeServlet");
					view.forward(request, response);
					
				}
				
			}
			
			if(success==false)
			{
				out.println("<b>Invalid Username or Password. Please try again !<b>");
			}

		}
		
		
		
		out.close();
		}
	
}