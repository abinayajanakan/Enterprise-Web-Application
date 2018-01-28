import java.util.*;
import java.io.*;

public class OrderDataStore{

	public HashMap<String, Order> Orders = new HashMap<String,Order>();
	public String orderDataStoreLocation = "C:/apache-tomcat-7.0.34/webapps/assignment1/WEB-INF/classes/OrderStore";

	void populateOrders(){

			ArrayList<String> orderItems = new ArrayList<String>();
			orderItems.add("Iphone7");
			orderItems.add("MotoG4");
			
			Order c1 = new Order("X9hwehdkjans", "87yguhjbuyjgh", "mshethwa@hawk.iit.edu", "10/03/2016", "10/20/2016", "Chicago", 74, orderItems);
			System.out.println("\t Order Id : "+c1.getorderId());
			Orders.put(c1.getcustomerEmailId(), c1);
			
	}

	//Fetching OrderHashMap from file
	public HashMap<String, Order> getOrderHashMap()
	{
		try{
			InputStream OrderStore = OrderDataStore.class.getResourceAsStream("OrderStore");
			ObjectInputStream ois=new ObjectInputStream(OrderStore);
			//System.out.println("hello");
			HashMap<String,Order> mapInFile=(HashMap<String,Order>)ois.readObject();

			ois.close();
			//fis.close();
			
			System.out.println("Inside OrderStore read method");
			for(Map.Entry<String,Order> m :mapInFile.entrySet()){
					System.out.println(m.getKey());
			Order c = m.getValue();
			System.out.println("\t Order Id : "+c.getorderId());
			System.out.println("\t Email Id : "+c.getcustomerEmailId());
			System.out.println("\t Total Amount : "+c.gettotalAmount());
			}
			
			return mapInFile;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception occured in reading or getting hashmap from file");
		}
		return null;
	}

	//writing Order hashmap to file
	public void  writeOrderHashMap(HashMap<String, Order> OrdersUpdated){

		try{
			File OrderHashmap=new File(orderDataStoreLocation);
			FileOutputStream fos=new FileOutputStream(OrderHashmap);
			ObjectOutputStream oos=new ObjectOutputStream(fos);

				oos.writeObject(OrdersUpdated);
				oos.flush();
				oos.close();
				fos.close();
			
		}catch(Exception e){
			System.out.println("Could NOT Write Orders to OrderHashMap ...");
		}
	}
	
// Private Methods to populate for first time

	private void  writeOrderHashMap(){

		try{
			File OrderHashmap=new File("OrderStore");
			FileOutputStream fos=new FileOutputStream(OrderHashmap);
				ObjectOutputStream oos=new ObjectOutputStream(fos);

				oos.writeObject(Orders);
				oos.flush();
				oos.close();
				fos.close();
			
		}catch(Exception e){
			System.out.println("Hey Could NOT Write Orders to OrderHashMap ...");
		}
	}
	
	private void readOrderHashmap() {

		try{
			File OrderHashmap=new File("OrderStore");
			FileInputStream fis=new FileInputStream(OrderHashmap);
			ObjectInputStream ois=new ObjectInputStream(fis);

			HashMap<String,Order> mapInFile=(HashMap<String,Order>)ois.readObject();

			ois.close();
			fis.close();
			System.out.println("Inside read method");
			System.out.println("map in File: "+mapInFile);
			for(Map.Entry<String,Order> m :mapInFile.entrySet()){
					System.out.println(m.getKey());
			Order c = m.getValue();
			System.out.println("\t Order Id : "+c.getorderId());
			System.out.println("\t Email Id : "+c.getcustomerEmailId());
			System.out.println("\t Total Amount : "+c.gettotalAmount());
			}
		}catch(Exception e){
			
			System.out.println("Inside exception");
		}

	}


	private void testDrive(){
		//populateOrders();
		//writeOrderHashMap();
		readOrderHashmap();
		
	}
	
	
	public static void main(String args[]){
        OrderDataStore p = new OrderDataStore();
		p.testDrive();
	}
	
	
}