import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Iterator;

public class AutoCompleteServlet extends HttpServlet {

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


    private ServletContext context;

    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
				
		loadDataFromMySQL();

        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {

                Iterator it = productsMap.keySet().iterator();

                while (it.hasNext()) {
                    String id = (String) it.next();
                    Product product = (Product) productsMap.get(id);

                    if ( // targetId matches product name
                         product.getName().toLowerCase().startsWith(targetId) ||
                         // targetId matches product company
                         product.getCompany().toLowerCase().startsWith(targetId)
                         )
					 {

                        sb.append("<product>");
                        sb.append("<id>" + product.getId() + "</id>");
                        sb.append("<name>" + product.getName() + "</name>");
                        sb.append("<company>" + product.getCompany() + "</company>");
                        sb.append("</product>");
                        namesAdded = true;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {

            // put the target composer in the request scope to display 
            //if ((targetId != null) && productsMap.containsKey(targetId.trim())) {
                request.setAttribute("product", productsMap.get(targetId));
				request.setAttribute("productName", targetId);
                context.getRequestDispatcher("/ShowSearchedProductServlet").forward(request, response);
				//context.getRequestDispatcher("/Home.jsp").forward(request, response);
            //}
        }
    }
}
