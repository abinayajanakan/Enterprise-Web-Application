import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class AddProduct extends HttpServlet {
	
	ArrayList<Object> products;
	HashMap<String, Smartphone> smartphones;
	HashMap<String, Laptop> laptops;
	HashMap<String, Tablet> tablets;
	HashMap<String, Television> televisions;
	HashMap<String, Accessory> accessories;
	
	SAXParserForProducts sp = new SAXParserForProducts();
	
	void loadDataFromXML()
	{
		try{
			products = sp.loadDataStore();
			
			smartphones = (HashMap<String,Smartphone>)products.get(0);
			tablets = (HashMap<String, Tablet>)products.get(1);
			laptops = (HashMap<String, Laptop>)products.get(2);
			televisions = (HashMap<String, Television>)products.get(3);
			accessories = (HashMap<String, Accessory>)products.get(4);
			
		}catch(Exception E){
			System.out.println("Exception");
		}
	}

	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		
		loadDataFromXML();
	 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String type="";
		type = request.getParameter("type");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>smart portable</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"DataAnalyticsServlet\">Data Analytics</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Add Product</h3>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		
		out.println("<form action='/assignment4/AddProductToXML' method=\"post\">");
		
		out.println("<p><label>Product Type:</label>");
		out.println("<select name='productType'><option name='productType' value='Smartphone' selected>Smartphones</option><option name='productType' value='Tablet'>Tablets</option>");
		out.println("<option name='productType' value='Laptop'>Laptops</option><option name='productType' value='Television'>Televisions </option><option name='productType' value='Accessory'>Accessories </option></select></p>");
		
		out.println("<p><label>Id:</label>Will be generated automatically</p>");
		out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" /></p>");
		out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" /></p>");
		out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" /></p>");
		out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" /></p>");
		out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" /></p>");
		out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" /></p>");
		out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" /></p>");
		out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Add Product\" type=\"submit\" /></p>");
		out.println("</form>");
		
		out.println("</div></fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}


