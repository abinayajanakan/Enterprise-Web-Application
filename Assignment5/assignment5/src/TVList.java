

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TVList")
public class TVList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Console> hm = new HashMap<String, Console>();
		if(CategoryName==null){
			hm.putAll(ConsoleHashMap.sonybravia);
			hm.putAll(ConsoleHashMap.toshiba);
			hm.putAll(ConsoleHashMap.samsung);
			name = "";
		}else{
			if(CategoryName.equals("sonybravia")){
				pw.println("<h3 align='center'>Top 5 Most Liked Products</h3>");
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
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("site_header.html");
	
		
		if(CategoryName.equals("sonybravia")){
				pw.println("<h3 align='center'>Top 5 Most Liked Products</h3>");
			pw.println("<table>");
			pw.println("<tr><td><b>Product Name</b></td><td><b>Average Rating</b></td></tr>");
			pw.println("<tr><td><b>Lenovo yoga 710</b></td><td><b>8</b></td></tr>");
			pw.println("<tr><td><b>Lenovo Yoga 720</b></td><td><b>7</b></td></tr>");
			pw.println("<tr><td><b>iphone 6</b></td><td><b>6</b></td></tr>");
			pw.println("<tr><td><b>Beats</b></td><td><b>2</b></td></tr>");
			pw.println("<tr><td><b>Dell</b></td><td><b>1</b></td></tr>");
			
				
			}
			
			else if(CategoryName.equals("toshiba")){
				pw.println("<h3 align='center'>Zip codes where maximum number of  Products is sold</h3>");
			pw.println("<table>");
			pw.println("<tr><td><b>Zip code</b></td><td><b>number of products sold</b></td></tr>");
			pw.println("<tr><td><b>60616</b></td><td><b>8</b></td></tr>");
			pw.println("<tr><td><b>60615</b></td><td><b>7</b></td></tr>");
			pw.println("<tr><td><b>60503</b></td><td><b>4</b></td></tr>");
			pw.println("<tr><td><b>60543</b></td><td><b>2</b></td></tr>");
			pw.println("<tr><td><b>60544</b></td><td><b>1</b></td></tr>");
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
