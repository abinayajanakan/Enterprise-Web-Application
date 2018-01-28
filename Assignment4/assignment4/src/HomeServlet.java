import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class HomeServlet extends HttpServlet
{
	
	ArrayList<Object> products;
	HashMap<String, Smartphone> smartphones;
	HashMap<String, Laptop> laptops;
	HashMap<String, Tablet> tablets;
	HashMap<String, Television> televisions;
	
	SAXParserForProducts sp = new SAXParserForProducts();
	
	public void init()
	{
		try{
		products = sp.loadDataStore();
		
		smartphones = (HashMap<String,Smartphone>)products.get(0);
		laptops = (HashMap<String, Laptop>)products.get(1);
		tablets = (HashMap<String, Tablet>)products.get(2);
		televisions = (HashMap<String, Television>)products.get(3);
		}
		catch(Exception E)
		{
			System.out.println("Exception");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//The second method insertAllProductsToMySQLFromXML() fetches all the products from xml file and saves it in MySQL under 'products' table
		MySqlDataStoreUtilities.deleteAllProductsFromTableMySQL();
		MySqlDataStoreUtilities.insertAllProductsToMySQLFromXML();
		
		//RequestDispatcher view = request.getRequestDispatcher("Home.jsp");
		//view.forward(request, response);
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		out.println("<body onload=\"init()\">");
		out.println("<div id=\"container\">");
		out.println("<header>");
		out.println("<h1><a href=\"/\">smart<span>portable</span></a></h1>");
		out.println("<h2>Buy smart</h2>");
		
		out.println("<form  name='autofillform1' action=''>");
		out.println("<div name='autofillform'>");
		out.println("<strong>Search Products: </strong>");
		out.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'><div id='auto-row'>");
		out.println("<table border='0' id='complete-table' class='popupBox'></table>");
		out.println("</div></div></form></header>");
		
		out.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Smartphones\">SmartPhones</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Tablets\">Tablets</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Laptops\">Laptops</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Televisions\">Televisions</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Accessories\">Accessories</a></li>");
		out.println("<li><a href=\"LoginServlet\">Login</a></li>");
		out.println("<li><a href=\"SignUp.html\">SignUp</a></li>");
		out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
		
		out.println("<div id=\"body\"><section id=\"content\">");
		out.println("<article><h2>Welcome to smartportable, Price Match Guaranteed !</h2>");
		out.println("<p>smart portable brings to you the best products from the best retailers in America. We provide our customers excellent customer service. Lowest price guaranteed across all the major retailers in America.</p>	");
		out.println("<p>We specialize in providing high quality products to our customers. Making our customers happy is our prime goal.</p>");
		out.println("<p>Currently we sell the following products online as well as in store.</p>");
		out.println("<ul class=\"styledlist\"><li>Smartphones</li><li>Tablets</li><li>Laptops</li><li>Tv's</li></ul>");

		out.println("</article><article><h2>Best Sellers</h2></article>");
		out.println("<article class=\"expanded\"><table style=\"width:100%\" style=\"height:100%\" border=\"1\" bordercolor=\"#aaa\" cellspacing=\"0\" cellpadding=\"0\">");
		out.println("<tr><td width=\"30%\"><a href=\"#\"><img style=\"width:200px;height:200px;\" style=\"display:block;\"  src=\"images/iPhone 6.jpg\" /></a>");
		out.println("</td><td width=\"40%\"><table>");
		out.println("<tr><td width=\"40\"><b>Apple iPhone 7</b></td></tr><tr><td width=\"40\"><b>Color:</b> Black</td></tr>");
		out.println("<tr><td><b>Condition:</b> New</td></tr>");
		out.println("</table></td><td width=\"30%\"><table>");
		out.println("<tr><td><b>Price: $749.00</b></td></tr>");
		out.println("<tr><td><a href=\"#\" class=\"button\">Reviews</a></td></tr>");
		out.println("<tr><td><a href=\"#\" class=\"button\">Add to Cart</a></td></tr>");
		out.println("</table></td></tr></table></article></section>");
		
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
		out.println("<p>&copy; smart portable. by Abinaya Janakan</p></div></footer></div>");
		out.println("</body></html>");
		
		out.close();

	}
}