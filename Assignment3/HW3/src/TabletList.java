

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

@WebServlet("/TabletList")
public class TabletList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Console> hm = new HashMap<String, Console>();
	
		
		/* if(CategoryName==null){
			hm.putAll(ConsoleHashMap.sonybravia);
			hm.putAll(ConsoleHashMap.toshiba);
			hm.putAll(ConsoleHashMap.samsung);
			name = "";
		}else{
			if(CategoryName.equals("sonybravia")){
				//pw.println("<h3 align='center'>Top 5 Most Liked Products</h3>");
			//pw.println("<table>");
			//pw.println("<tr><td><b>Product Name</b></td><td><b>Average Rating</b></td></tr>");
				hm.putAll(ConsoleHashMap.sonybravia);
				name = ConsoleHashMap.string_sonybravia;
			}
			else if(CategoryName.equals("toshiba")){
				hm.putAll(ConsoleHashMap.toshiba);
				name = ConsoleHashMap.string_toshiba;
			}
			else if(CategoryName.equals("samsung")){
				hm.putAll(ConsoleHashMap.samsung);
				name = ConsoleHashMap.string_samsung;
			}
		}
		
	    */
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("site_header.html");
	
		
		
			
			 if(CategoryName.equals("sale")){
				pw.println("<h3 align='center'>chart</h3>");
				pw.println("<div align='center' id=\"body\"><article><h3 align=\"center\">Select any one query from the folowing and click Execute Query</h3>");
				pw.println("<fieldset>");

		pw.println("<form action='TabletList' method='post'>");
		pw.println("<p><select name='queryType'>");
		pw.println("<option name='queryType' value='1'>Sales report</option>");
		pw.println("<option name='queryType' value='2'>Chart for product availabe vs no of products</option>");
		pw.println("<option name='queryType' value='3'>Products currently available on sale</option>");
		pw.println("<option name='queryType' value='4'>products available in manufaturer rebate</ </option>");
		
		
		
		pw.println("</select></p>");
		pw.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
		pw.println("</form>");
		
		
		pw.println("</fieldset></article</div></div></body></html>");
		
		pw.close();
	 
	}
	helper.printHtml("site_sidebar.html");
		helper.printHtml("site_footer.html");
			
			}
			
 public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String queryType = "";
		queryType = request.getParameter("queryType");
		
		pw.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<title>Smart portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		pw.println("<body><div id=\"container\">");
		pw.println("<header><h1><a href=\"/\">Smart<span>portables</span></a></h1><h2>Buy Smart!</h2><h3>Store Manager Portal</h3></header>");
		pw.println("<nav><ul>");
		pw.println("<li class=\"\"><a href=\"Home\">Home</a></li>");
		pw.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		pw.println("<li><a href=\"DataAnalyticsServlet\">Data Analytics</a></li>");
		pw.println("<li><a href=\"LogpwServlet\">Login</a></li></ul></nav>");
		
	
		pw.println("<div align='center' id=\"body\"><article><h3 align=\"center\">Select any one query from the folowing and click Execute Query</h3>");
		pw.println("<fieldset>");

		if(queryType.equals("1"))
		{
			pw.println("<form action='TabletList' method='post'>");
			pw.println("<p><select name='queryType'>");
			pw.println("<option name='queryType' value='1'>Sales report</option>");
		pw.println("<option name='queryType' value='2'>Chart for product availabe vs no of products</option>");
		pw.println("<option name='queryType' value='3'>Products currently available on sale</option>");
		pw.println("<option name='queryType' value='4'>products available in manufaturer rebate</ </option>");
		pw.println("</select></p>");
			pw.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			pw.println("</form>");
			LinkedHashMap<String, ArrayList<Object>> SalesReport;
			SalesReport = MySqlDataStoreUtilities.getSalesReport();
			
			String prod_name;
			float price;
			int num_available;
				pw.println("<h3 align='center'>Sales report</h3>");
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
			
			pw.println("</table>");
		}
		
		
		if(queryType.equals("2"))
		{
			pw.println("<form action='DataAnalyticsServlet' method='post'>");
			pw.println("<p><select name='queryType'>");
			pw.println("<option name='queryType' value='1'>Sales report</option>");
		pw.println("<option name='queryType' value='2'>Chart for product availabe vs no of products</option>");
		pw.println("<option name='queryType' value='3'>Products currently available on sale</option>");
		pw.println("<option name='queryType' value='4'>products available in manufaturer rebate</ </option>");
		pw.println("</select></p>");
			pw.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			pw.println("</form>");
			LinkedHashMap<String, ArrayList<Object>> SalesReport;
			SalesReport = MySqlDataStoreUtilities.getSalesReport();
			
			String prod_name;
			float price;
			int num_available;
				pw.println("<h3 align='center'>Top 5 Most Liked Products</h3>");
			request.getRequestDispatcher("chart.html").forward(request, response);
			
				
		}
		
		
		if(queryType.equals("3"))
		{
			pw.println("<form action='TabletList' method='post'>");
			pw.println("<p><select name='queryType'>");
			pw.println("<option name='queryType' value='1'>Sales report</option>");
		pw.println("<option name='queryType' value='2'>Chart for product availabe vs no of products</option>");
		pw.println("<option name='queryType' value='3'>Products currently available on sale</option>");
		pw.println("<option name='queryType' value='4'>products available in manufaturer rebate</ </option>");
		pw.println("</select></p>");
			pw.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			pw.println("</form>");
			LinkedHashMap<String, ArrayList<Object>> Sales;
			Sales = MySqlDataStoreUtilities.Sales();
			
			String prod_name;
			
				pw.println("<h3 align='center'>products on sale</h3>");
			pw.println("<table>");
			pw.println("<tr><td><b>Product Name</b></td></tr>");
			
			
			for(Map.Entry<String, ArrayList<Object>> m :Sales.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				prod_name = (String)values.get(0);
				
				
				pw.println("<tr><td>"+prod_name+"</td>");
				
			}
			
			pw.println("</table>");
		}
		if(queryType.equals("4"))
		{
			pw.println("<form action='TabletList' method='post'>");
			pw.println("<p><select name='queryType'>");
			pw.println("<option name='queryType' value='1'>Sales report</option>");
		pw.println("<option name='queryType' value='2'>Chart for product availabe vs no of products</option>");
		pw.println("<option name='queryType' value='3'>Products currently available on sale</option>");
		pw.println("<option name='queryType' value='4'>products available in manufaturer rebate</ </option>");
		pw.println("</select></p>");
			pw.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			pw.println("</form>");
			LinkedHashMap<String, ArrayList<Object>> Rebate;
			Rebate = MySqlDataStoreUtilities.Rebate();
			
			String prod_name;
			
				pw.println("<h3 align='center'>products on manufaturer rebate</h3>");
			pw.println("<table>");
			pw.println("<tr><td><b>Product Name</b></td></tr>");
			
			
			for(Map.Entry<String, ArrayList<Object>> m :Rebate.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				prod_name = (String)values.get(0);
				
				
				pw.println("<tr><td>"+prod_name+"</td>");
				
			}
			
			pw.println("</table>");
		}
		
		
		pw.println("</fieldset></article</div></div></body></html>");
		
		pw.close();
	}
			
			 
	 
	}
	
			
			

			
				
		
				
	
		
	

