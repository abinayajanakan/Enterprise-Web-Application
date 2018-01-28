import java.util.*;
import java.io.*;

public class PopulateCustomersHashmap{

	public HashMap<String, Customer> customers = new HashMap<String,Customer>();
	//public ArrayList<Customer> customersArrayList = new ArrayList<Customer>();
	public String customerStoreLocation = "C:/apache-tomcat-7.0.34/webapps/assignment1/WEB-INF/classes/CustomerStore";

	void populateCustomers(){

			Customer c1 = new Customer("Mohammed", "Shethwala", "mshethwa@hawk.iit.edu", "password", "9913897786");
			System.out.println("\t Customer Id : "+c1.getcustomerId());
			customers.put(c1.getemailId(), c1);
			
			Customer c2 = new Customer("Aftab", "Shethwala", "aftab@hawk.iit.edu", "password", "9913897786");
			System.out.println("\t Customer Id : "+c2.getcustomerId());
			customers.put(c2.getemailId(), c2);
	}

	//Fetching CustomerHashMap from file
	public HashMap<String, Customer> getCustomerHashMap()
	{
		try{
			InputStream customerStore = PopulateCustomersHashmap.class.getResourceAsStream("CustomerStore");
			//File customerHashmap=new File(customerStoreLocation);
			//FileInputStream fis=new FileInputStream(customerHashmap);
			ObjectInputStream ois=new ObjectInputStream(customerStore);
			//System.out.println("hello");
			HashMap<String,Customer> mapInFile=(HashMap<String,Customer>)ois.readObject();

			ois.close();
			//fis.close();
			
			System.out.println("Inside read method");
			for(Map.Entry<String,Customer> m :mapInFile.entrySet()){
					System.out.println(m.getKey());
			Customer c = m.getValue();
			System.out.println("\t Customer Id : "+c.getcustomerId());
			System.out.println("\t First Name : "+c.getfirstName());
			System.out.println("\t Last Name : "+c.getlastName());
			}
			
			return mapInFile;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception occured in reading or getting hashmap from file");
		}
		return null;
	}

	//writing customer hashmap to file
	public void  writeCustomerHashMap(HashMap<String, Customer> customersUpdated){

		try{
			File customerHashmap=new File(customerStoreLocation);
			FileOutputStream fos=new FileOutputStream(customerHashmap);
			ObjectOutputStream oos=new ObjectOutputStream(fos);

				oos.writeObject(customersUpdated);
				oos.flush();
				oos.close();
				fos.close();
			
		}catch(Exception e){
			System.out.println("Could NOT Write customers to CustomerHashMap ...");
		}
	}
	
// Private Methods to populate for first time

	private void  writeCustomerHashMap(){

		try{
			File customerHashmap=new File("CustomerStore");
			FileOutputStream fos=new FileOutputStream(customerHashmap);
				ObjectOutputStream oos=new ObjectOutputStream(fos);

				oos.writeObject(customers);
				oos.flush();
				oos.close();
				fos.close();
			
		}catch(Exception e){
			System.out.println("Hey Could NOT Write customers to CustomerHashMap ...");
		}
	}
	
	private void readcustomerHashmap() {

		try{
			File customerHashmap=new File("CustomerStore");
			FileInputStream fis=new FileInputStream(customerHashmap);
			ObjectInputStream ois=new ObjectInputStream(fis);

			HashMap<String,Customer> mapInFile=(HashMap<String,Customer>)ois.readObject();

			ois.close();
			fis.close();
			System.out.println("Inside read method");
			System.out.println("map in File: "+mapInFile);
			for(Map.Entry<String,Customer> m :mapInFile.entrySet()){
					System.out.println(m.getKey());
			Customer c = m.getValue();
			System.out.println("\t Customer Id : "+c.getcustomerId());
			System.out.println("\t First Name : "+c.getfirstName());
			System.out.println("\t Last Name : "+c.getlastName());
			}
		}catch(Exception e){
			
			System.out.println("Inside exception");
		}

	}


	private void testDrive(){
		populateCustomers();
		writeCustomerHashMap();
		readcustomerHashmap();
		
	}
	
	
	public static void main(String args[]){
        PopulateCustomersHashmap p = new PopulateCustomersHashmap();
		p.testDrive();
	}
	
	
}