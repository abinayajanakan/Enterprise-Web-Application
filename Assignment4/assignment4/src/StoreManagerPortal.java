import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;



public class StoreManagerPortal extends HttpServlet {
	
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
		
		String productType = "";
		productType = request.getParameter("productType");
		
		String type="";
		productType = request.getParameter("type");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>Buy smart</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"DataAnalyticsServlet\">Data Analytics</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Product List</h3>");
		out.println("<fieldset>");
		
		//----- Printing Smartphones
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Smartphones</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Id</b></td><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
		for(Map.Entry<String,Smartphone> m :smartphones.entrySet())
		{
			Smartphone s = m.getValue();
			
			String id=s.getId();
			String retailer=s.getRetailer();
			String image=s.getImage();
			String name=s.getName();
			String company=s.getCompany();
			String condition=s.getCondition();
			String color=s.getColor();
			float price=s.getPrice();
			
			out.println("<form action='/assignment4/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='smartphone'>");
			out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment4/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='type' value='Smartphone'>");
			
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		//out.println("</form>");
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing Tablets
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Tablets</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Id</b></td><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
		for(Map.Entry<String,Tablet> m :tablets.entrySet())
		{
			Tablet s = m.getValue();
			
			String id=s.getId();
			String retailer=s.getRetailer();
			String image=s.getImage();
			String name=s.getName();
			String company=s.getCompany();
			String condition=s.getCondition();
			String color=s.getColor();
			float price=s.getPrice();
			
			out.println("<form action='/assignment4/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='tablet'>");
			out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment4/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='type' value='Tablet'>");
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		//-----Printing Laptops
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Laptops</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Id</b></td><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
		for(Map.Entry<String,Laptop> m :laptops.entrySet())
		{
			Laptop s = m.getValue();
			
			String id=s.getId();
			String retailer=s.getRetailer();
			String image=s.getImage();
			String name=s.getName();
			String company=s.getCompany();
			String condition=s.getCondition();
			String color=s.getColor();
			float price=s.getPrice();
			
			out.println("<form action='/assignment4/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='laptop'>");
			out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment4/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='type' value='Laptop'>");
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		//-----Printing Televisions
	
		out.println("<div id=\"body\"><article><h3 align=\"center\">Televisions</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Id</b></td><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
		for(Map.Entry<String,Television> m :televisions.entrySet())
		{
			Television s = m.getValue();
			
			String id=s.getId();
			String retailer=s.getRetailer();
			String image=s.getImage();
			String name=s.getName();
			String company=s.getCompany();
			String condition=s.getCondition();
			String color=s.getColor();
			float price=s.getPrice();
			
			out.println("<form action='/assignment4/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='television'>");
			out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment4/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='type' value='Television'>");
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		
		out.println("</table>");
		out.println("</fieldset></article></div>");
		
		
		//----- Printing Accessories
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Accessories</h3>");
		out.println("<fieldset>");
		out.println("<table>");
		out.println("<tr><td><b>Id</b></td><td><b>Retailer</b></td><td><b>Name</b></td><td><b>Company</b></td><td><b>Condition</b></td><td><b>Color</b></td></tr>");
		
		for(Map.Entry<String,Accessory> m :accessories.entrySet())
		{
			Accessory s = m.getValue();
			
			String id=s.getId();
			String retailer=s.getRetailer();
			String image=s.getImage();
			String name=s.getName();
			String company=s.getCompany();
			String condition=s.getCondition();
			String color=s.getColor();
			float price=s.getPrice();
			
			out.println("<form action='/assignment4/DeleteProduct' method='post'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='type' value='accessory'>");
			out.println("<td>"+id+"</td>");
			out.println("<td>"+retailer+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+company+"</td>");
			out.println("<td>"+condition+"</td>");
			out.println("<td>"+color+"</td>");
			out.println("<td><input class='formbutton' type='submit'  value='Delete'></td>");
			out.println("</form>");
			
			out.println("<form action='/assignment4/UpdateProduct' method='get'>");
			out.println("<input type='hidden' name='productName' value='"+name+"'>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			out.println("<input type='hidden' name='retailer' value='"+retailer+"'>");
			out.println("<input type='hidden' name='image' value='"+image+"'>");
			out.println("<input type='hidden' name='company' value='"+company+"'>");
			out.println("<input type='hidden' name='condition' value='"+condition+"'>");
			out.println("<input type='hidden' name='color' value='"+color+"'>");
			out.println("<input type='hidden' name='price' value='"+price+"'>");
			out.println("<input type='hidden' name='type' value='Accessory'>");
			
			out.println("<td><input class='formbutton' type='submit'  value='Update'></td></tr>");
			out.println("</form>");
			
		}
		//out.println("</form>");
		out.println("</table>");
		out.println("</fieldset></article</div>");
		
		
		out.println("</fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}


