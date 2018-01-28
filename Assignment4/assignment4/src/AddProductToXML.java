import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class AddProductToXML extends HttpServlet {
	
	ArrayList<Object> products;
	HashMap<String, Smartphone> smartphones;
	HashMap<String, Laptop> laptops;
	HashMap<String, Tablet> tablets;
	HashMap<String, Television> televisions;
	HashMap<String, Accessory> accessories;
	
	SAXParserForProducts sp = new SAXParserForProducts();
	
	String smartphonesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/classes/SmartphoneCatalog.xml";
	String tabletsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/classes/TabletCatalog.xml";
	String laptopsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/classes/LaptopCatalog.xml";
	String televisionsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/classes/TelevisionCatalog.xml";
	String accessoriesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/classes/AccessoryCatalog.xml";
	
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
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>smart portables</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>Buy smart</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		
		Random r = new Random();
		int Low = 1;
		int High = 50000;
		int R = r.nextInt(High-Low);
		String id = ""+R;
		
		String productType = request.getParameter("productType");
		String retailer = request.getParameter("retailer");
		String imagePath = request.getParameter("imagePath");
		String productName = request.getParameter("productName");
		String company = request.getParameter("company");
		String condition = request.getParameter("condition");
		String price = request.getParameter("price");
		String color = request.getParameter("color");
		//System.out.println("type: "+productType);
		
		Float pricee = Float.parseFloat(price);
		
		
		if(retailer!=null && !retailer.equals("") && imagePath!=null && !imagePath.equals("") && productName!=null && !productName.equals("")
			&& company!=null && !company.equals("") && condition!=null && !condition.equals("") && price!=null && !price.equals("")
			&& color!=null && !color.equals(""))
		{
			
			MySqlDataStoreUtilities.insertProductInMySQL(id, productType, retailer, imagePath, productName, company, condition, pricee, color);
			
			if(productType.equals("Smartphone"))
			{	
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(smartphonesXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("smartphone");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("smartPhoneImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("smartPhoneName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("smartPhoneCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("smartPhoneCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("smartPhonePrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("smartPhoneColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(smartphonesXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
			
				out.println("<p>Smartphone with id: " +id+" Added Successfully<p>");
			}
			
			if(productType.equals("Tablet"))
			{
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(tabletsXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("tablet");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("tabletImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("tabletName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("tabletCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("tabletCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("tabletPrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("tabletColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(tabletsXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
				
				out.println("<p>Tablet with id: " +id+" Added Successfully<p>");
			}
			
			if(productType.equals("Laptop"))
			{
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(laptopsXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("laptop");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("laptopImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("laptopName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("laptopCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("laptopCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("laptopPrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("laptopColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(laptopsXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
				
				out.println("<p>Laptop with id: " +id+" Added Successfully<p>");
			}
			
			if(productType.equals("Television"))
			{
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(televisionsXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("tv");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("tvImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("tvName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element smartPhoneCompany = document.createElement("tvCompany");
						smartPhoneCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(smartPhoneCompany);

						Element smartPhoneCondition = document.createElement("tvCondition");
						smartPhoneCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(smartPhoneCondition);
						
						Element smartPhonePrice = document.createElement("tvPrice");
						smartPhonePrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(smartPhonePrice);
						
						Element smartPhoneColor = document.createElement("tvColor");
						smartPhoneColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(smartPhoneColor);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(televisionsXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
				
				out.println("<p>Television with id: " +id+" Added Successfully<p>");
			}
			
			if(productType.equals("Accessory"))
			{	
				try{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(accessoriesXmlFileName);
					Element root = document.getDocumentElement();

						Element newProduct = document.createElement("accessory");
						newProduct.setAttribute("id", id);
						newProduct.setAttribute("retailer", retailer);
						
						Element image = document.createElement("accessoryImage");
						image.appendChild(document.createTextNode(imagePath));
						newProduct.appendChild(image);
						
						Element name = document.createElement("accessoryName");
						name.appendChild(document.createTextNode(productName));
						newProduct.appendChild(name);
						
						Element accessoryCompany = document.createElement("accessoryCompany");
						accessoryCompany.appendChild(document.createTextNode(company));
						newProduct.appendChild(accessoryCompany);

						Element accessoryCondition = document.createElement("accessoryCondition");
						accessoryCondition.appendChild(document.createTextNode(condition));
						newProduct.appendChild(accessoryCondition);
						
						Element accessoryPrice = document.createElement("accessoryPrice");
						accessoryPrice.appendChild(document.createTextNode(price));
						newProduct.appendChild(accessoryPrice);
						
						Element accessoryColor = document.createElement("accessoryColor");
						accessoryColor.appendChild(document.createTextNode(color));
						newProduct.appendChild(accessoryColor);

						root.appendChild(newProduct);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(accessoriesXmlFileName);
					transformer.transform(source, result);
				}
				catch(Exception e)
				{
					out.println("<p>Error adding product to xml file<p>");
					e.printStackTrace();
				}
			
				out.println("<p>Accessory with id: " +id+" Added Successfully<p>");
			}
			

		}
		
		else
		{
			out.println("<p>Please go back and fill all details properly");
		}
		
		
		out.println("</div></fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}


