import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class UpdateProduct extends HttpServlet {
	
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

	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		
		loadDataFromXML();
	 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String type="";
		type = request.getParameter("type");
		
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Best Deal</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		//----------------------------------------------------------
		
		String productType = request.getParameter("type");
		String id = request.getParameter("id");
		String retailer = request.getParameter("retailer");
		String imagePath = request.getParameter("image");
		String productName = request.getParameter("productName");
		String company = request.getParameter("company");
		String condition = request.getParameter("condition");
		String price = request.getParameter("price");
		String color = request.getParameter("color");
		
		out.println("<div id=\"body\"><article><h3 align=\"center\">Update product</h3>");
		out.println("<fieldset><div style=\"width:400px; margin-right:auto; margin-left:auto;\">");
		
		
		if(productType.equals("Smartphone"))
		{
			out.println("<form action='/assignment4/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Smartphone"+"</p>");
			out.println("<input type='hidden' name='type' value='Smartphone'>");
			out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Tablet"))
		{
			out.println("<form action='/assignment4/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Tablet"+"</p>");
			out.println("<input type='hidden' name='type' value='Tablet'>");
			out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Laptop"))
		{
			out.println("<form action='/assignment4/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Laptop"+"</p>");
			out.println("<input type='hidden' name='type' value='Laptop'>");
			out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Television"))
		{
			out.println("<form action='/assignment4/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Television"+"</p>");
			out.println("<input type='hidden' name='type' value='Television'>");
			out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		if(productType.equals("Accessory"))
		{
			out.println("<form action='/assignment4/UpdateProduct' method=\"post\">");
			out.println("<p><label>Product Type:</label>"+"Accessory"+"</p>");
			out.println("<input type='hidden' name='type' value='Accessory'>");
			out.println("<p><label>Id:</label>"+id+"</p>");
			out.println("<input type='hidden' name='id' value='"+id+"'>");
			
			out.println("<p><label>Retailer:</label><input name=\"retailer\" type=\"text\" value='"+retailer+"'/></p>");
			out.println("<p><label>Image Path:</label><input name=\"imagePath\" type=\"text\" value='"+imagePath+"'/></p>");
			out.println("<p><label>Product Name:</label><input name=\"productName\" type=\"text\" value='"+productName+"' /></p>");
			out.println("<p><label>Company:</label><input name=\"company\" type=\"text\" value='"+company+"' /></p>");
			out.println("<p><label>Condition:</label><input name=\"condition\" type=\"text\" value='"+condition+"' /></p>");
			out.println("<p><label>Price:</label><input name=\"price\" type=\"text\" value='"+price+"' /></p>");
			out.println("<p><label>Color:</label><input name=\"color\" type=\"text\" value='"+color+"' /></p>");
			out.println("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Update Product\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		out.println("</div></fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		loadDataFromXML();
	 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String type="";
		type = request.getParameter("type");
		
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Best Deal</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"StoreManagerPortal?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
		//----------------------------------------------------------
	  
		String productType = request.getParameter("type");
		String id = request.getParameter("id");
		String retailer = request.getParameter("retailer");
		String imagePath = request.getParameter("imagePath");
		String productName = request.getParameter("productName");
		String company = request.getParameter("company");
		String condition = request.getParameter("condition");
		String price = request.getParameter("price");
		String color = request.getParameter("color");
		
		Float pricee = Float.parseFloat(price);
	
		MySqlDataStoreUtilities.updateProductFromMySQL(id, productType, retailer, imagePath, productName, company, condition, pricee, color);
		
	  
		if(productType.equals("Smartphone"))
		{
			try
			{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(smartphonesXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								   Node node = list.item(j);

								   if ("smartPhoneImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("smartPhoneName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("smartPhoneCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("smartPhoneCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("smartPhonePrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("smartPhoneColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(smartphonesXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>Smartphone with id: " +id+" Updated Successfully<p>");
	  
		}
		
		if(productType.equals("Tablet"))
		{
			try
			{
				System.out.println("Inside tablet if");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(tabletsXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("tabletImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("tabletName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("tabletCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("tabletCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("tabletPrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("tabletColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(tabletsXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>Tablet with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Laptop"))
		{
			try
			{
				//System.out.println("Inside tablet if");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(laptopsXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("laptopImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("laptopName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("laptopCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("laptopCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("laptopPrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("laptopColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(laptopsXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>Laptop with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Television"))
		{
			try
			{
				//System.out.println("Inside tablet if");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(televisionsXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								//System.out.println("Inside second for");
								   Node node = list.item(j);

								   if ("tvImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("tvName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("tvCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("tvCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("tvPrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("tvColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(televisionsXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>Television with id: " +id+" Updated Successfully<p>");
		}
		
		if(productType.equals("Accessory"))
		{
			try
			{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(accessoriesXmlFileName);
				
				Element docEle = doc.getDocumentElement();
				
				NodeList nl = docEle.getChildNodes();
				if (nl != null && nl.getLength() > 0)
				{
					//System.out.println("Inside first if");
					for (int i = 0; i < nl.getLength(); i++)
					{
						//System.out.println("Inside first for");
						if (nl.item(i).getNodeType() == Node.ELEMENT_NODE)
						{
							//System.out.println("Inside second if");
							Element el = (Element) nl.item(i);
							if(id.equals(el.getAttributes().getNamedItem("id").getNodeValue()))
							{
								//System.out.println("Inside third if");
								NodeList list = el.getChildNodes();
								
								for (int j = 0; j < list.getLength(); j++)
								{
								   Node node = list.item(j);

								   if ("accessoryImage".equals(node.getNodeName())) {
									node.setTextContent(imagePath);
								   }
								   if ("accessoryName".equals(node.getNodeName())) {
									node.setTextContent(productName);
								   }
								   if ("accessoryCompany".equals(node.getNodeName())) {
									node.setTextContent(company);
								   }
								   if ("accessoryCondition".equals(node.getNodeName())) {
									node.setTextContent(condition);
								   }
								   if ("accessoryPrice".equals(node.getNodeName())) {
									node.setTextContent(price);
								   }
								   if ("accessoryColor".equals(node.getNodeName())) {
									node.setTextContent(color);
								   }
								   
								}
								
							}
							
						}
					}
				}
	
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(accessoriesXmlFileName));
				transformer.transform(source, result);
				
			}
			catch(Exception e)
			{
				out.println("<p>Error updating product to xml file<p>");
				e.printStackTrace();
			}
			
			out.println("<p>Accessory with id: " +id+" Updated Successfully<p>");
	  
		}
		
		
		
	}
			
}


