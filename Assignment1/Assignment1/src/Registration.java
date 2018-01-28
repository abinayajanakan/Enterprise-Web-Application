

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String usertype = "customer";
		if(!helper.isLoggedin())
			usertype = request.getParameter("usertype");
		
		
		
		if(!password.equals(repassword)){
			error_msg = "Passwords doesn't match!";
		}else{
			HashMap<String, User> hm = new HashMap<String, User>();
			if (usertype.equals("customer")) {
				hm.putAll(UserHashMap.customer);
			} else if (usertype.equals("retailer")) {
				hm.putAll(UserHashMap.retailer);
			} else if (usertype.equals("manager")) {
				hm.putAll(UserHashMap.manager);
			}
			
			if(hm.containsKey(username))
				error_msg = "Username already exist as " + usertype;
			else{
				User user = new User(username,password,usertype);
				if (usertype.equals("customer")) {
					UserHashMap.customer.put(username, user);
				} else if (usertype.equals("retailer")) {
					UserHashMap.retailer.put(username, user);
				} else if (usertype.equals("manager")) {
					UserHashMap.manager.put(username, user);
				}
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Your "+usertype+" account has been created. Please login");
				if(!helper.isLoggedin()){
					response.sendRedirect("Login"); return;
				} else {
					response.sendRedirect("Account"); return;
				}
			}
		}
		if(helper.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", error_msg);
			response.sendRedirect("Account"); return;
		}
		displayRegistration(request, response, pw, true);
		
	}

	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if (error) {
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		}
		pw.print("<form method='post' action='Registration'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Re-Password</h3></td><td><input type='password' name='repassword' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='retailer'>Store Manager</option><option value='manager'>Salesman</option></select>"
				+ "</td></tr></table>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</div></div></div>");
		helper.printHtml("site_footer.html");
	}
}
