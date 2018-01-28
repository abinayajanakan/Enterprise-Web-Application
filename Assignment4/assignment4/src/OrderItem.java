
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class OrderItem implements java.io.Serializable {
	
	String orderId;
	String itemType;
    String itemName;
	int itemId;
	float itemPrice;
    int itemQty;
	String orderDate;
    String deliveryDate;
	String customerEmailId;
	String deliveryAddress;
	
  
    public OrderItem(String orderId, String itemType, String itemName, int itemId, float itemPrice, int itemQty, String orderDate, String deliveryDate, String customerEmailId, String deliveryAddress){
		
		this.orderId = orderId;
		this.itemType = itemType;
		this.itemName = itemName;
		this.itemId = itemId;
		this.itemPrice = itemPrice;
		this.itemQty = itemQty;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.customerEmailId = customerEmailId;
		this.deliveryAddress = deliveryAddress;
		
    }

	void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
			return orderId;
		}
		
	void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public String getItemType() {
			return itemType;
		}
		
	void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemName() {
			return itemName;
		}
		
	void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemId() {
			return itemId;
		}
		
	void setItemPrice(Float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Float getItemPrice() {
			return itemPrice;
		}
		
	void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}

	public int getItemQty() {
			return itemQty;
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
	
	

}
