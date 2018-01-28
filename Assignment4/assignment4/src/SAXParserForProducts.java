/*********
http://www.saxproject.org/
The following URL from Oracle is the JAVA documentation for the API
https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html
*********/

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.io.*;

////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SAXParserForProducts extends DefaultHandler {
	
	Smartphone smartphone;
	Laptop laptop;
	Tablet tablet;
	Television television;
	Accessory accessory; 
    
	HashMap<String,Smartphone> smartphones;
	HashMap<String,Laptop> laptops;
	HashMap<String,Tablet> tablets;
	HashMap<String,Television> televisions;
	HashMap<String,Accessory> accessories;

	ArrayList<Object> products;
	
    String smartphonesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/ProductCatalog.xml";
	String tabletsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/TabletCatalog.xml";
	String laptopsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/LaptopCatalog.xml";
	String televisionsXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/TelevisionCatalog.xml";
	String accessoriesXmlFileName = "C:/apache-tomcat-7.0.34/webapps/assignment4/WEB-INF/AccessoryCatalog.xml";
	
    String elementValueRead;
	
	int x=1;
	int y=1;
	int z=1;
	int tv=1;
	int a=1;

    public ArrayList<Object> loadDataStore() {
        
		smartphones = new HashMap<String,Smartphone>();
		laptops = new HashMap<String,Laptop>();
		tablets = new HashMap<String,Tablet>();
		televisions = new HashMap<String,Television>();
		accessories = new HashMap<String,Accessory>();
		
		products = new ArrayList<Object>();
		
        parseDocument(smartphonesXmlFileName);
		parseDocument(tabletsXmlFileName);
		parseDocument(laptopsXmlFileName);
		parseDocument(televisionsXmlFileName);
		parseDocument(accessoriesXmlFileName);
		
		//printSmartPhones();
		//printTablets();
		//printLaptops();
		//printAccessories();
		
		products.add(smartphones);
		products.add(tablets);
		products.add(laptops);
		products.add(televisions);
		products.add(accessories);
		
		return products;
    }

    private void parseDocument(String xmlFileName) {
		
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
			File f = new File(xmlFileName);
			parser.parse(f, this);
            //parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
	
	
	public void printSmartPhones()
	{
		for(Map.Entry<String,Smartphone> m :smartphones.entrySet())
		{
			System.out.println(m.getKey());
			Smartphone s = m.getValue();
			System.out.println("\t Id : "+s.getId());
			System.out.println("\t Company : "+s.getCompany());
			System.out.println("\t Price : "+s.getPrice());
		}	
	}
	public void printTablets()
	{
		for(Map.Entry<String,Tablet> m :tablets.entrySet())
		{
			System.out.println(m.getKey());
			Tablet t = m.getValue();
			System.out.println("\t Id : "+t.getId());
			System.out.println("\t Company : "+t.getCompany());
			System.out.println("\t Price : "+t.getPrice());
		}	
	}
	public void printLaptops()
	{
		for(Map.Entry<String,Laptop> m :laptops.entrySet())
		{
			System.out.println(m.getKey());
			Laptop s = m.getValue();
			System.out.println("\t Id : "+s.getId());
			System.out.println("\t Company : "+s.getCompany());
			System.out.println("\t Price : "+s.getPrice());
		}	
	}

	public void printAccessories()
	{
		System.out.println("Inside printAccessories");
		for(Map.Entry<String,Accessory> m :accessories.entrySet())
		{
			System.out.println(m.getKey());
			Accessory s = m.getValue();
			System.out.println("\t Id : "+s.getId());
			System.out.println("\t Company : "+s.getCompany());
			System.out.println("\t Price : "+s.getPrice());
		}	
	}
////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.

There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////



    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("product")) {
            smartphone = new Smartphone();
            smartphone.setId(attributes.getValue("id"));
           // smartphone.setRetailer(attributes.getValue("retailer"));
			//System.out.println("smartphone element start");
        }
		
		
		if (elementName.equalsIgnoreCase("tablet")) {
            tablet = new Tablet();
            tablet.setId(attributes.getValue("id"));
            tablet.setRetailer(attributes.getValue("retailer"));
        }
		
		if (elementName.equalsIgnoreCase("laptop")) {
            laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
            laptop.setRetailer(attributes.getValue("retailer"));
        }
		
		if (elementName.equalsIgnoreCase("tv")) {
            television = new Television();
            television.setId(attributes.getValue("id"));
            television.setRetailer(attributes.getValue("retailer"));
        }
		
		if (elementName.equalsIgnoreCase("accessory")) {
            accessory = new Accessory();
            accessory.setId(attributes.getValue("id"));
            accessory.setRetailer(attributes.getValue("retailer"));
        }
		
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
//Fetching values of smartphones
		 
        if (element.equals("product")) {
			smartphones.put("S"+x, smartphone);
			x++;
			
	    return;
        }
        if (element.equalsIgnoreCase("imageUrl")) {
            smartphone.setImage(elementValueRead);
		
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            smartphone.setName(elementValueRead);
			
	    return;
        }
		if (element.equalsIgnoreCase("description")) {
            smartphone.setCompany(elementValueRead);
			
	    return;
        }
		if (element.equalsIgnoreCase("display_under")) {
            smartphone.setCondition(elementValueRead);
			
	    return;
        }
		if(element.equalsIgnoreCase("price")){
            smartphone.setPrice(Float.parseFloat(elementValueRead));
			
	    return;
        }
        if(element.equalsIgnoreCase("discounAmt")){
           smartphone.setColor(Float.parseFloat(elementValueRead));
		  
	    return;
        }
		
		
	 
//Fetching values of Tablets

		if (element.equals("tablet")) {
			tablets.put("T"+y, tablet);
			y++;
	    return;
        }
        if (element.equalsIgnoreCase("tabletImage")) {
            tablet.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("tabletName")) {
            tablet.setName(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("tabletCompany")) {
            tablet.setCompany(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("tabletCondition")) {
            tablet.setCondition(elementValueRead);
	    return;
        }
		if(element.equalsIgnoreCase("tabletPrice")){
            tablet.setPrice(Float.parseFloat(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("tabletColor")){
           tablet.setColor(elementValueRead);
	    return;
        }
		if(element.equalsIgnoreCase("tabletDescription")){
            tablet.setDescription(elementValueRead);
			//tablets.put("T", tablet);
	    return;
        }
		
	 
// Fetching values of Laptops

		if (element.equals("laptop")) {
			laptops.put("L"+z, laptop);
			z++;
	    return;
        }
        if (element.equalsIgnoreCase("laptopImage")) {
            laptop.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("laptopName")) {
            laptop.setName(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("laptopCompany")) {
            laptop.setCompany(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("laptopCondition")) {
            laptop.setCondition(elementValueRead);
	    return;
        }
		if(element.equalsIgnoreCase("laptopPrice")){
            laptop.setPrice(Float.parseFloat(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("laptopColor")){
           laptop.setColor(elementValueRead);
	    return;
        }
		if(element.equalsIgnoreCase("laptopDescription")){
            laptop.setDescription(elementValueRead);
			//laptops.put("L", laptop);
	    return;
        }
		
		
	
// Fetching values of Televisions

	

		if (element.equals("tv")) {
			televisions.put("Tv"+tv, television);
			tv++;
	    return;
        }
        if (element.equalsIgnoreCase("tvImage")) {
            television.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("tvName")) {
            television.setName(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("tvCompany")) {
            television.setCompany(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("tvCondition")) {
            television.setCondition(elementValueRead);
	    return;
        }
		if(element.equalsIgnoreCase("tvPrice")){
            television.setPrice(Float.parseFloat(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("tvColor")){
           television.setColor(elementValueRead);
	    return;
        }
		if(element.equalsIgnoreCase("tvDescription")){
            television.setDescription(elementValueRead);
			//televisions.put("Tv", television);
	    return;
        }
		
		
//Fetching values of accessories
		 
        if (element.equals("accessory")) {
			accessories.put("A"+a, accessory);
			a++;
			
	    return;
        }
        if (element.equalsIgnoreCase("accessoryImage")) {
            accessory.setImage(elementValueRead);
		
	    return;
        }
        if (element.equalsIgnoreCase("accessoryName")) {
            accessory.setName(elementValueRead);
			
	    return;
        }
		if (element.equalsIgnoreCase("accessoryCompany")) {
            accessory.setCompany(elementValueRead);
			
	    return;
        }
		if (element.equalsIgnoreCase("accessoryCondition")) {
            accessory.setCondition(elementValueRead);
			
	    return;
        }
		if(element.equalsIgnoreCase("accessoryPrice")){
            accessory.setPrice(Float.parseFloat(elementValueRead));
			
	    return;
        }
        if(element.equalsIgnoreCase("accessoryColor")){
           accessory.setColor(elementValueRead);
		  
	    return;
        }
		if(element.equalsIgnoreCase("accessoryDescription")){
            accessory.setDescription(elementValueRead);
	    return;
        }
		
}	
		
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
		//System.out.println(elementValueRead);
    }
	
	
	public static void main(String[] args) {
		 SAXParserForProducts sp = new SAXParserForProducts();
         sp.loadDataStore();
		 //sp.printAccessories();
		 //sp.printSmartPhones();
    }
	
	
}
