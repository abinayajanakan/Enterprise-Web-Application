import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.*;
import java.util.*;

public class DealMatches implements java.io.Serializable {
	
	HashMap<String, Product> selectedProducts;
	
	HashMap<String, Product> productMap;
	
	ArrayList<Object> products;
	
	ArrayList<String> tweets = new ArrayList<String>();
	
	public ArrayList<String> getTweets()
	{
		return tweets;
	}
	
	public HashMap<String, Product> getSelectedProductsFromTweets()
	{
		try{
			//Process p = Runtime.getRuntime().exec("python C:\\apache-tomcat-7.0.34\\webapps\\assignment5\\BestBuyDealMatches.ipynb");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		selectedProducts = new HashMap<String, Product>();
		
		products = MySqlDataStoreUtilities.getProductsFromMySQL();
		
		productMap = (HashMap<String, Product>)products.get(5);
		
		tweets = new ArrayList<String>();
		
		String line=null;
		
		try
		{
			for(Map.Entry<String, Product> entry: productMap.entrySet())
			{
				if(selectedProducts.size()<2 && !selectedProducts.containsKey(entry.getKey()))
				{
					BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\apache-tomcat-7.0.34\\webapps\\assignment5\\DealMatches.txt")));
					
					line = reader.readLine();
					
					if(line==null)
					{
						
					}
					
					else
					{
						do{
							if(line.contains(entry.getKey()))
							{
								tweets.add(line);
								selectedProducts.put(entry.getKey(), entry.getValue());
								//System.out.println("Tweet: " + line);
								//System.out.println("Product: " + entry.getKey());
								break;
							}
							
						}while ((line=reader.readLine()) != null);
					}
				
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return selectedProducts;
		
	}
	
	public static void main(String args[]){
		
		DealMatches dm = new DealMatches();
		
		dm.getSelectedProductsFromTweets();
		
		
	}

}
