
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Order implements java.io.Serializable {
	
	String orderId;
	String confirmationNo;
    String customerEmailId;
    String orderDate;
    String deliveryDate;
	String deliveryAddress;
	float totalAmount;
	ArrayList<String> orderItems;
	//HashMap<String, List<Object>> itemsHashMap;
	
  
    public Order(String orderId, String confirmationNo, String customerEmailId, String orderDate, String deliveryDate, String deliveryAddress, float totalAmount, ArrayList<String> orderItems){
		//orderItems = new ArrayList<String>();
		this.orderId = orderId;
		this.confirmationNo = confirmationNo;
		this.customerEmailId = customerEmailId;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.deliveryAddress = deliveryAddress;
		this.totalAmount = totalAmount;
		this.orderItems = orderItems;
    }

	void setorderId(String orderId) {
		this.orderId = orderId;
	}

	public String getorderId() {
			return orderId;
		}
		
	void settotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float gettotalAmount() {
			return totalAmount;
		}
		
	void setdeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getdeliveryAddress() {
			return deliveryAddress;
		}

	void setconfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}

	public String getconfirmationNo() {
			return confirmationNo;
		}

	void setcustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getcustomerEmailId() {
			return customerEmailId;
		}

	void setorderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getorderDate() {
			return orderDate;
		}

	void setdeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getdeliveryDate() {
			return deliveryDate;
		}

	void addItem(String item) {
		orderItems.add(item);
	}

	public ArrayList<String> getOrderItems() {
			return orderItems;
		}
		
	/*
	public HashMap<String, List<Object>> getItemsHashMap()
	{
		return itemsHashMap;
	}
	
	public void setItemsHashMap(HashMap<String, List<Object>> itemsHashMap)
	{
		this.itemsHashMap = itemsHashMap;
	}
	*/
}
