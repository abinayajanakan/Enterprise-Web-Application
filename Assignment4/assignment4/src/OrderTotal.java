
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class OrderTotal implements java.io.Serializable {
	
	String orderId;
	String orderDate;
    String deliveryDate;
	float totalAmount;
    String customerEmailId;
    String deliveryAddress;
	
	HashMap<String, OrderItem> orderItems;
  
    public OrderTotal(String orderId, String orderDate, String deliveryDate, float totalAmount, String customerEmailId, String deliveryAddress){
		
		orderItems = new HashMap<String, OrderItem>();
		
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.totalAmount = totalAmount;
		this.customerEmailId = customerEmailId;
		this.deliveryAddress = deliveryAddress;
    }

	void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
			return orderId;
		}
		
	void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDate() {
			return orderDate;
		}

	void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryDate() {
			return deliveryDate;
		}
		
	void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float getTotalAmount() {
			return totalAmount;
		}
		
	void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getCustomerEmailId() {
			return customerEmailId;
		}
		
	void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryAddress() {
			return deliveryAddress;
		}


	void setOrderItems(HashMap<String, OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}
	
	public HashMap<String, OrderItem> getOrderItems()
	{
		return orderItems;
	}
	
}
