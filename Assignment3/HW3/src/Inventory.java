

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Inventory")
public class Inventory extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Console> hm = new HashMap<String, Console>();
	
		
		
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("site_header.html");
	
		
		if(CategoryName.equals("report")){
			LinkedHashMap<String, ArrayList<Object>> SalesReport;
			SalesReport = MySqlDataStoreUtilities.getSalesReport();
			
			String prod_name;
			float price;
			int num_available;
				pw.println("<h3 align='center'>Top 5 Most Liked Products</h3>");
			pw.println("<table>");
			pw.println("<tr><td><b>Product Name</b></td><td><b>Product Price</b></td><td><b>Quantity Sold</b></td></tr>");
			
			
			for(Map.Entry<String, ArrayList<Object>> m :SalesReport.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				prod_name = (String)values.get(0);
				price = (Float)values.get(1);
				num_available = (Integer)values.get(2);
				
				pw.println("<tr><td>"+prod_name+"</td>");
				pw.println("<td>"+price+"</td>");
				pw.println("<td>"+num_available+"</td></tr>");
			}
			
			
				
			}
			
			else if(CategoryName.equals("sale")){
				pw.println("<h3 align='center'>chart</h3>");
				pw.println("<div align='center' id=\"body\"><article><h3 align=\"center\">Select any one query from the folowing and click Execute Query</h3>");
				
			
			}
			else if(CategoryName.equals("samsung")){
			pw.println("<h3 align='center'>Top 5 products sold</h3>");	
			pw.println("<table>");
			pw.println("<tr><td><b>Asus Zen phone</b></td><td><b>12</b></td></tr>");
			pw.println("<tr><td><b>Lenovo Yoga 720</b></td><td><b> 11</b></td></tr>");
			pw.println("<tr><td><b>iphone 6</b></td><td><b>8</b></td></tr>");
			pw.println("<tr><td><b>Beats</b></td><td><b>6</b></td></tr>");
			pw.println("<tr><td><b>Dell</b></td><td><b>5</b></td></tr>");
			}
			
			
				helper.printHtml("site_sidebar.html");
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		//pw.print("<a style='font-size: 24px;'>"+name+" Consoles</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		
		
		pw.print("</table></div></div></div>");		
		helper.printHtml("site_footer.html");
		
	}
}
