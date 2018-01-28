import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet
{
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
		pch = new PopulateCustomersHashmap();
		customers = MySqlDataStoreUtilities.getCustomers();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	firstName = request.getParameter("firstName");
	lastName = request.getParameter("lastName");
	emailId = request.getParameter("emailId");
	phoneNumber = request.getParameter("phoneNumber");
	password = request.getParameter("password");
	rePassword = request.getParameter("rePassword");
	
	//checking if textboxes are empty or not
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
	
	//if passwords match, then do this
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
			out.println("<p>Email Id already exists</p>");
			out.println("<p>Please go back and try again !</p>");
		}
		else
		{
			MySqlDataStoreUtilities.insertCustomer(firstName, lastName, emailId, password, phoneNumber);
			
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
		
		
		//Old code done with the help of Serialized file
		/*
		customer = new Customer(firstName, lastName, emailId, password, phoneNumber);
		customers = pch.getCustomerHashMap();
		customers.put(customer.getemailId(), customer);
		pch.writeCustomerHashMap(customers);
		pch.getCustomerHashMap();
		*/
		/*
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>smart portable</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head><body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>Buy smart</h2></header>");
		out.println("<h3 align=\"center\">Account Created Successfully. Please Login to continue</h3>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		out.println("<form action=\"/assignment1/LoginServlet\" method=\"get\">");
		out.println("<p><label for=\"name\">Email Id:</label>");
		out.println("<input name=\"name\" id=\"name\" value=\"\" type=\"text\" /></p>");
		out.println("<p><label for=\"email\">Password:</label>");
		out.println("<input name=\"email\" id=\"email\" value=\"\" type=\"password\" /></p>");
		out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Login\" type=\"submit\" /></p>");
		out.println("</form></div></fieldset></div></body></html>");
		*/
	}
	
	else
	{
		out.println("<h3>The following error may have occured:<h3>");
		out.println("<p>1: Any one field may have been kept empty</p>");
		out.println("<p>2: Passwords do not match</p>");
		out.println("<p>Please go back and try again !</p>");
	}
	
	out.close();
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
}