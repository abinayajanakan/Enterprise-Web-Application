import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ShowSearchedProductServlet extends HttpServlet
{
	ArrayList<Object> products;
	HashMap<String, Smartphone> smartphones;
	HashMap<String, Laptop> laptops;
	HashMap<String, Tablet> tablets;
	HashMap<String, Television> televisions;
	HashMap<String, Accessory> accessories;
	
	HashMap<String, Product> productsMap = new HashMap<String, Product>();
	
	public void init()
	{
		//loadDataFromMySQL();
	}
	
	void loadDataFromMySQL()
	{
		try{
		products = AjaxUtility.getProductsFromMySQL();
		
		smartphones = (HashMap<String,Smartphone>)products.get(0);
		tablets = (HashMap<String, Tablet>)products.get(1);
		laptops = (HashMap<String, Laptop>)products.get(2);
		televisions = (HashMap<String, Television>)products.get(3);
		accessories = (HashMap<String, Accessory>)products.get(4);
		
		productsMap = (HashMap<String, Product>)products.get(5);
		
		}catch(Exception E){
		System.out.println("Exception");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		loadDataFromMySQL();
		
		HttpSession session = request.getSession(false);
		String fname = null;
		
		if(session != null)
		{
			fname=(String)session.getAttribute("firstName");
		}
		
		
		String productName = (String)request.getAttribute("productName");
		String productType = "Products";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Best Deal</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		out.println("<body onload='init()'><div id=\"container\"><header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2>");
		out.println("<form  name='autofillform1' action='autocomplete'>");
		out.println("<div name='autofillform'>");
		out.println("<strong>Search Products: </strong>");
		out.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'>");
		out.println("<div id='auto-row'>");
		out.println("<table border='0' id='complete-table' class='popupBox'></table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		
		out.println("</header>");
		
		if(fname != null && !fname.isEmpty() && session != null)
		{
			System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(fname);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"LoggedInHomeServlet\">Home</a></li>");
		}
		else
		{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}
		
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Smartphones\">SmartPhones</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Tablets\">Tablets</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Laptops\">Laptops</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Televisions\">Televisions</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Accessories\">Accessories</a></li>");
		
		if(fname != null && !fname.isEmpty() && session != null)
		{
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
			out.println("<li><a href=\"ViewOrders\">Your Orders</a></li>");
			out.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			out.println("<li><a href=\"LoginServlet\">Login</a></li>");
			out.println("<li><a href=\"SignUp.html\">SignUp</a></li>");
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
		}
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
		
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		
//Code for Smartphones		
		if(productType.equals("Products"))
		{
			out.println("<article><h3>Searched Product</h3></article>");
			out.println("<article class=\"expanded\">");
			
			for(Map.Entry<String,Product> m :productsMap.entrySet()){
				
				Product s = m.getValue();
				
				String name = s.getName();
				
				if (name != null) {
					name = name.trim().toLowerCase();
				}
				
				//System.out.println("productName: "+productName);
				//System.out.println("s.getName(): "+s.getName());
				if(productName.equals(name))
				{
					out.println("<table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr><td width=\"30%\">");
					out.println("<a href=\"ProductDetails.html\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"");
					out.println(s.getImage());
					out.println("\" /></a>");
					out.println("</td>");
					out.println("<td width=\"40%\"><table><tr><td width=\"40\"><b>");
					out.println(s.getName());
					out.println("</b></td></tr><tr><td width=\"40\"><b>Company:</b>");
					out.println(s.getCompany());
					out.println("</b></td></tr><tr><td width=\"40\"><b>Color:</b>");
					out.println(s.getColor());
					out.println("</td></tr><tr><td><b>Condition:</b>");
					out.println("New");
					out.println("</td></tr></table></td>");
					out.println("<td width=\"30%\"><table><tr><td><b>Price:");
					out.println(s.getPrice());
					
					if(fname != null && !fname.isEmpty())
					{
						out.println("<tr><td><form method = 'get' action = 'WriteReview'>");
						out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Write Review'>");
						out.println("<input type='hidden' name='productType' value='"+productType+"'>");
						out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
						out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
						out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
						out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
						out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
						out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
						out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
						out.println("<input type='hidden' name='quantity' value='"+1+"'>");
						out.println("</form></td></tr>");
						
						out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
						out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
						out.println("<input type='hidden' name='productType' value='"+productType+"'>");
						out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
						out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
						out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
						out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
						out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
						out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
						out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
						out.println("<input type='hidden' name='quantity' value='"+1+"'>");
						out.println("</form></td></tr>");
						
						out.println("<tr><td><form method = 'get' action = 'AddToCart'>");
						out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'Add to Cart'>");
						out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
						out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
						out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
						out.println("<input type='hidden' name='quantity' value='"+1+"'>");
						out.println("</form></td></tr>");
						
						out.println("</table></td></tr></table>");
					}
					else
					{
						out.println("</b></td></tr>");
						out.println("<tr><td><form method = 'get' action = 'ViewReviews'>");
						out.println("<input class = 'formbutton' type = 'submit' name = '"+ s.getName() +"' value = 'View Reviews'>");
						out.println("<input type='hidden' name='productType' value='"+productType+"'>");
						out.println("<input type='hidden' name='productName' value='"+s.getName()+"'>");
						out.println("<input type='hidden' name='image' value='"+s.getImage()+"'>");
						out.println("<input type='hidden' name='price' value='"+s.getPrice()+"'>");
						out.println("<input type='hidden' name='color' value='"+s.getColor()+"'>");
						out.println("<input type='hidden' name='condition' value='"+s.getCondition()+"'>");
						out.println("<input type='hidden' name='company' value='"+s.getCompany()+"'>");
						out.println("<input type='hidden' name='retailer' value='"+s.getRetailer()+"'>");
						out.println("<input type='hidden' name='quantity' value='"+1+"'>");
						out.println("</form></td></tr>");
						out.println("</table></td></tr></table>");
					}
			
				}
			
		}
	}
		
		out.println("</article></section>");
		//out.println("<div class=\"clear\"></div></div></div>");
		
		//Sidebar starts	
		out.println("<aside class=\"sidebar\">");
		
		out.println("<ul><li><h4>Trending</h4><ul>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveLiked\">Top 5 Liked Products</a></li>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveZipCodes\">Top 5 Zip Codes</a></li>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveSold\">Top 5 Sold Products</a></li></ul></li>");
		
		out.println("<ul><li><h4>SmartPhones</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=Samsung\">Samsung</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=HTC\">HTC</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=Motorola\">Motorola</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=LG\">LG</a></li></ul></li>");
		
		out.println("<ul><li><h4>Tablets</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=Samsung\">Samsung</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=Acer\">Acer</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=Amazon\">Amazon</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=LG\">LG</a></li></ul></li>");
		
		out.println("<ul><li><h4>Laptops</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Dell\">Dell</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=HP\">HP</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Lenovo\">Lenovo</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Microsoft\">Microsoft</a></li></ul></li>");
		
		out.println("<ul><li><h4>Tv's</h4><ul><li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=LG\">LG</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=Samsung\">Samsung</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=Vizio\">Vizio</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=Sony\">Sony</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=Sharp\">Sharp</a></li>");
		
		out.println("</ul></li></aside><div class=\"clear\"></div></div>");
		
	//Footer starts	
		out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
		out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
		out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
		out.println("<p>&copy; BestDeal 2016. by Mohammed Shethwala</p></div></footer></div>");
		
		
		out.println("</body></html>");

		out.close();
	}
	
}