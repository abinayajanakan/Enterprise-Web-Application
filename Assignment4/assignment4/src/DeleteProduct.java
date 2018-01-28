import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class DeleteProduct extends HttpServlet {
	
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
		out.println("<title>smart portable</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">smart<span>portable</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		out.println("<fieldset><div style=\"width:800px; margin-right:auto; margin-left:auto;\">");
		
		
		String productName = request.getParameter("productName");
		String type = request.getParameter("type");
		
		MySqlDataStoreUtilities.deleteProductFromMySQL(productName);
		
		if(type.equals("smartphone"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(smartphonesXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("smartphone");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element smartphone = (Element)nodes.item(i);
					  // <smartPhoneName>
					  Element smartPhoneName = (Element)smartphone.getElementsByTagName("smartPhoneName").item(0);
					  String sName = smartPhoneName.getTextContent();
					  if (sName.equals(productName))
					  {
						 smartphone.getParentNode().removeChild(smartphone);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(smartphonesXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		
		if(type.equals("tablet"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(tabletsXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("tablet");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element tablet = (Element)nodes.item(i);
					  // <tabletName>
					  Element tabletName = (Element)tablet.getElementsByTagName("tabletName").item(0);
					  String sName = tabletName.getTextContent();
					  if (sName.equals(productName))
					  {
						 tablet.getParentNode().removeChild(tablet);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(tabletsXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		if(type.equals("laptop"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(laptopsXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("laptop");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element laptop = (Element)nodes.item(i);
					  // <laptopName>
					  Element laptopName = (Element)laptop.getElementsByTagName("laptopName").item(0);
					  String sName = laptopName.getTextContent();
					  if (sName.equals(productName))
					  {
						 laptop.getParentNode().removeChild(laptop);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(laptopsXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		
		if(type.equals("television"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(televisionsXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("tv");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element tv = (Element)nodes.item(i);
					  // <tvName>
					  Element tvName = (Element)tv.getElementsByTagName("tvName").item(0);
					  String sName = tvName.getTextContent();
					  if (sName.equals(productName))
					  {
						 tv.getParentNode().removeChild(tv);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(televisionsXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		if(type.equals("accessory"))
		{	
			try
			{
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(accessoriesXmlFileName);
				Element root = document.getDocumentElement();

				NodeList nodes = document.getElementsByTagName("accessory");

					for (int i = 0; i < nodes.getLength(); i++)
					{
					  Element accessory = (Element)nodes.item(i);
					  // <accessoryName>
					  Element accessoryName = (Element)accessory.getElementsByTagName("accessoryName").item(0);
					  String sName = accessoryName.getTextContent();
					  if (sName.equals(productName))
					  {
						 accessory.getParentNode().removeChild(accessory);
					  }
					}

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult(accessoriesXmlFileName);
				transformer.transform(source, result);
			}
			catch(Exception e)
			{
				out.println("<p>Error deleting product from xml file<p>");
				e.printStackTrace();
			}
		
			out.println("<p>Product with name: " +productName+" Deleted Successfully from Product List<p>");
		}
		
		
		out.println("</div></fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		doPost(request, response);
	}
			
}


